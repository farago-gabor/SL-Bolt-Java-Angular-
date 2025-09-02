package hu.projekt.bolt.service;

import hu.projekt.bolt.dto.RendelesDTO;
import hu.projekt.bolt.dto.RendelesTetelDTO;
import hu.projekt.bolt.mapper.DolgozoMapper;
import hu.projekt.bolt.mapper.RendelesMapper;
import hu.projekt.bolt.model.Dolgozo;
import hu.projekt.bolt.model.Rendeles;
import hu.projekt.bolt.model.RendelesTetel;
import hu.projekt.bolt.repository.ArucikkRepository;
import hu.projekt.bolt.repository.RendelesRepository;
import hu.projekt.bolt.repository.RendelesTetelRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RendelesServiceImpl implements RendelesService{

    private final RendelesRepository rendelesRepository;
    private final RendelesTetelRepository rendelesTetelRepository;
    private final ArucikkRepository arucikkRepository;
    private final RendelesMapper rendelesMapper;
    private final DolgozoServiceImpl dolgozoService;
    private final DolgozoMapper dolgozoMapper;

    @Override
    public RendelesDTO ujRendeles(String email, String telefonszam, LocalDate hatarido, int dolgozoId, List<RendelesTetelDTO> tetelek) {
        Rendeles rendeles = new Rendeles();
        rendeles.setEmail(email);
        rendeles.setTelefonszam(telefonszam);
        rendeles.setHatarido(hatarido);
        rendeles.setFelvetteDolgozo(dolgozoMapper.toEntity(dolgozoService.getDolgozoById(dolgozoId)));

        rendelesRepository.save(rendeles);

        for (RendelesTetelDTO tetel : tetelek) {
            RendelesTetel rendelesTetel = new RendelesTetel();
            rendelesTetel.setRendeles(rendeles);
            rendelesTetel.setMennyiseg(tetel.getMennyiseg());
            rendelesTetel.setMegjegyzes(tetel.getMegjegyzes());

            rendelesTetel.setArucikk(arucikkRepository.findByMegnevezes(tetel.getArucikkNev())
                    .orElseThrow(() -> new RuntimeException("Nem található az árucikk")));

            rendelesTetelRepository.save(rendelesTetel);
        }

        RendelesDTO rendelesDTO = rendelesMapper.rendelesToDto(rendeles);
        rendelesDTO.setTetelek(tetelek);

        return rendelesDTO;
    }

    @Override
    public RendelesDTO modositStatusz(int id, boolean beerkezet, boolean felreteve, boolean szoltam, boolean elvitte) {
        Rendeles rendeles = rendelesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("A rendelés nem található"));


        rendeles.setBeerkezett(beerkezet);
        rendeles.setFelreteve(felreteve);
        rendeles.setSzoltam(szoltam);
        rendeles.setElvitte(elvitte);
        rendelesRepository.save(rendeles);

        RendelesDTO dto = rendelesMapper.rendelesToDto(rendeles);
        dto.setTetelek(rendelesMapper.rendelesTetelListaToDto(rendelesTetelRepository.findByRendelesId(rendeles.getId())));

        return dto;
    }

    @Override
    public void torolRendeles(int id) {
        rendelesRepository.deleteById(id);
    }

    @Override
    public List<RendelesDTO> osszesRendeles() {

       return rendelesRepository.findAll()
                .stream()
                .map(r -> {
                    RendelesDTO dto = rendelesMapper.rendelesToDto(r);
                    dto.setTetelek(rendelesMapper.rendelesTetelListaToDto(rendelesTetelRepository.findByRendelesId(r.getId())));
                    return dto;
                })
                .toList();
    }

    @Override
    public List<RendelesTetelDTO> getRendelesTetelek(int rendelesId) {
        return rendelesMapper.rendelesTetelListaToDto(rendelesTetelRepository.findByRendelesId(rendelesId));
    }

    @Override
    public RendelesDTO getRendelesById(int id) {

        return rendelesRepository.findById(id)
                .map(r -> {
                    RendelesDTO dto = rendelesMapper.rendelesToDto(r);
                    dto.setTetelek(rendelesMapper.rendelesTetelListaToDto(rendelesTetelRepository.findByRendelesId(r.getId())));
                    return dto;
                })
                .orElseThrow(() -> new RuntimeException("Rendelés nem található"));

    }
}
