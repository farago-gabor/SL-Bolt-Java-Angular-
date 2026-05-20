package hu.projekt.bolt.service;

import hu.projekt.bolt.dto.TevekenysegDTO;
import hu.projekt.bolt.dto.TevekenysegNaploDTO;
import hu.projekt.bolt.mapper.TevekenysegMapperImpl;
import hu.projekt.bolt.model.*;
import hu.projekt.bolt.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TevekenysegServiceImpl implements TevekenysegService{

    private final TevekenysegRepository tevekenysegRepository;
    private final TevekenysegGyakorisagRepository gyakorisagRepository;
    private final TevekenysegNaploRepository naploRepository;
    private final DolgozoRepository dolgozoRepository;
    private final TevekenysegIdopontRepository idopontRepository;
    private final TevekenysegMapperImpl mapper;



    @Override
    public List<TevekenysegDTO> maiElvegzendoFeladatok() {
        LocalDate today = LocalDate.now();
        String todayName = switch (today.getDayOfWeek()) {
            case MONDAY    -> "hetfo";
            case TUESDAY   -> "kedd";
            case WEDNESDAY -> "szerda";
            case THURSDAY  -> "csutortok";
            case FRIDAY    -> "pentek";
            case SATURDAY  -> "szombat";
            case SUNDAY    -> "vasarnap";
        };

        List<TevekenysegDTO> result = new ArrayList<>();

        for (TevekenysegGyakorisag gyak : gyakorisagRepository.findAll()) {
            Tevekenyseg tevekenyseg = gyak.getTevekenyseg();

            if (!isAktualisMa(gyak, today, todayName)) {
                continue;
            }

            // Mai napra eső összes időpont
            for (TevekenysegIdopont idopont : gyak.getIdopontok()) {
                if (!idopont.getNap().equalsIgnoreCase(todayName)) {
                    continue;
                }

                // Már elvégezve ma erre az időpontra?
                boolean marElvegezve = naploRepository.existsByTevekenysegAndIdopontAndDatum(
                        tevekenyseg, idopont, today);

                if (marElvegezve) {
                    continue; // már megvan ma ez az időpont
                }

                // Új DTO minden egyes elvégzendő időpontra!
                TevekenysegDTO dto = mapper.toTevekenysegDTO(tevekenyseg, gyak);

                // Csak ezt az egy időpontot tesszük bele
                dto.setIdopontok(List.of(mapper.idopontToDto(idopont)));

                result.add(dto);
            }
        }

        return result;
    }

    // segédfüggvény, hogy jól vizsgáljuk a 2 és 3 heti ismétlődést
    private boolean isAktualisMa(TevekenysegGyakorisag gyak, LocalDate today, String todayName) {
        List<TevekenysegIdopont> idopontok = gyak.getIdopontok();
        TevekenysegGyakorisag.Gyakorisag tipus = gyak.getGyakorisag();
        LocalDate kezdoDatum = gyak.getKezdoDatum();

        switch (tipus) {
            case mindig:
                return true;

            case egyszeri:
                return kezdoDatum != null && kezdoDatum.equals(today);

            case heti:
                // Ha heti ismétlődés, akkor a napokat az idopontokban kell keresni
                return idopontok != null && idopontok.stream()
                        .anyMatch(idopont -> idopont.getNap().equalsIgnoreCase(todayName));

            case ketheti:
            case haromheti:
                // Ha kethavi vagy haromhavi ismétlődés, akkor ellenőrizzük a napot, és a kezdődátumot is
                if (idopontok == null || idopontok.stream().noneMatch(idopont -> idopont.getNap().equalsIgnoreCase(todayName))) {
                    return false;
                }
                if (kezdoDatum == null) return false;

                long weeksBetween = ChronoUnit.WEEKS.between(kezdoDatum, today);
                int modulo = tipus == TevekenysegGyakorisag.Gyakorisag.ketheti ? 2 : 3;

                return weeksBetween % modulo == 0;

            default:
                return false;
        }
    }


    @Override
    public void feladatElvegzese(int tevekenysegId, int idopontId, int dolgozoId, LocalDate datum) {
        Tevekenyseg tevekenyseg = tevekenysegRepository.findById(tevekenysegId)
                .orElseThrow(() -> new RuntimeException("Tevekenyseg nem található: " + tevekenysegId));

        Dolgozo dolgozo = dolgozoRepository.findById(dolgozoId)
                .orElseThrow(() -> new RuntimeException("Dolgozó nem található: " + dolgozoId));

        TevekenysegIdopont idopont = idopontRepository.findById(idopontId)
                .orElseThrow(() -> new RuntimeException("Idopont nem található: " + idopontId));


        boolean alreadyLogged = naploRepository.existsByTevekenysegAndIdopontAndDatum(tevekenyseg, idopont, datum);
        if (alreadyLogged) return;

        TevekenysegNaplo naplo = new TevekenysegNaplo();
        naplo.setTevekenyseg(tevekenyseg);
        naplo.setIdopont(idopont);
        naplo.setDolgozo(dolgozo);
        naplo.setDatum(datum);

        naploRepository.save(naplo);
    }

    @Override
    public List<TevekenysegNaploDTO> maiElvegzettFeladatok() {
        LocalDate today = LocalDate.now();

        List<TevekenysegNaplo> naplok = naploRepository.findAllByDatum(today);
        return naplok.stream()
                .map(mapper::TevekenysegNaploToDTO)
                .toList();
    }

    @Override
    public Page<TevekenysegNaploDTO> osszesNaplobejegyzes(Pageable pageable) {
        Page<TevekenysegNaplo> naplok = naploRepository.findAll(pageable);
        return naplok.map(mapper::TevekenysegNaploToDTO);
    }

    @Override
    public void ujTevekenyseg(TevekenysegDTO dto) {
        Tevekenyseg tevekenyseg = new Tevekenyseg();
        tevekenyseg.setMegnevezes(dto.getMegnevezes());
        tevekenyseg.setLeiras(dto.getLeiras());
        tevekenysegRepository.save(tevekenyseg);

        TevekenysegGyakorisag gyak = new TevekenysegGyakorisag();
        gyak.setTevekenyseg(tevekenyseg);
        gyak.setGyakorisag(TevekenysegGyakorisag.Gyakorisag.valueOf(dto.getGyakorisag().toLowerCase()));
        gyak.setKezdoDatum(dto.getKezdoDatum());
        // Mapstruct segítségével alakítjuk DTO → entitás
        List<TevekenysegIdopont> idopontok = mapper.mapDTOsToIdopontok(dto.getIdopontok());
        // Állítsuk be a gyakoriság kapcsolatot az időpontokban is
        for (TevekenysegIdopont i : idopontok) {
            i.setGyakorisag(gyak);
        }

        gyak.setIdopontok(idopontok);
        gyakorisagRepository.save(gyak);
    }

    @Override
    public List<TevekenysegDTO> osszesTevekenyseg() {
        List<TevekenysegGyakorisag> gyakorisagok = gyakorisagRepository.findAll();

        return gyakorisagok.stream()
                .map(gyak -> mapper.toTevekenysegDTO(gyak.getTevekenyseg(), gyak))
                .toList();
    }

    @Override
    public void modositTevekenyseg(int tevekenysegId, TevekenysegDTO dto) {
        Tevekenyseg tevekenyseg = tevekenysegRepository.findById(tevekenysegId)
                .orElseThrow(() -> new RuntimeException("Tevékenység nem található: " + tevekenysegId));

        tevekenyseg.setMegnevezes(dto.getMegnevezes());
        tevekenyseg.setLeiras(dto.getLeiras());
        tevekenysegRepository.save(tevekenyseg);


        TevekenysegGyakorisag gyak = gyakorisagRepository.findByTevekenyseg(tevekenyseg)
                .orElseThrow(() -> new RuntimeException("Gyakoriság nem található"));

        gyak.setGyakorisag(TevekenysegGyakorisag.Gyakorisag.valueOf(dto.getGyakorisag().toUpperCase()));
        gyak.setKezdoDatum(dto.getKezdoDatum());

        // 🔄 Töröljük a meglévő időpontokat és újakat állítunk be
        gyak.getIdopontok().clear(); // csak ha CascadeType.ALL + orphanRemoval = true van beállítva!
        List<TevekenysegIdopont> ujIdopontok = mapper.mapDTOsToIdopontok(dto.getIdopontok());
        for (TevekenysegIdopont i : ujIdopontok) {
            i.setGyakorisag(gyak);
        }
        gyak.setIdopontok(ujIdopontok);

        gyakorisagRepository.save(gyak);
    }

    @Override
    public void torolTevekenyseg(int id) {

        Tevekenyseg tevekenyseg = tevekenysegRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("A tevékenység nem található"));

        gyakorisagRepository.deleteByTevekenyseg(tevekenyseg);
        tevekenysegRepository.delete(tevekenyseg);
    }

    public Page<TevekenysegNaploDTO> osszesNaplobejegyzesLapozva(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // page index 0-tól
        Page<TevekenysegNaplo> naplok = naploRepository.findAllByOrderByDatumDesc(pageable);

        return naplok.map(mapper::TevekenysegNaploToDTO);
    }
}
