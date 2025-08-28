package hu.projekt.bolt.service;

import hu.projekt.bolt.dto.DolgozoDTO;
import hu.projekt.bolt.mapper.DolgozoMapper;
import hu.projekt.bolt.model.Dolgozo;
import hu.projekt.bolt.repository.DolgozoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DolgozoServiceImpl implements DolgozoService {
    private final DolgozoRepository dolgozoRepository;
    private final DolgozoMapper dolgozoMapper;

    @Override
    public DolgozoDTO ujDolgozo(String nev, String email, String jelszo) {
        Dolgozo dolgozo = new Dolgozo();
        dolgozo.setNev(nev);
        dolgozo.setEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        dolgozo.setJelszo(encoder.encode(jelszo));
        dolgozo.setSzerepkor("dolgozo");
        return dolgozoMapper.toDto(dolgozoRepository.save(dolgozo));
    }

    @Override
    public DolgozoDTO modositDolgozo(Long id, String nev, String email, String szerepkor) {
        Dolgozo dolgozo = dolgozoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dolgozó nem található"));

        dolgozo.setNev(nev);
        dolgozo.setEmail(email);
        dolgozo.setSzerepkor(szerepkor);

        return dolgozoMapper.toDto(dolgozoRepository.save(dolgozo));
    }

    @Override
    public DolgozoDTO modositJelszo(Long id, String ujJelszo) {
        Dolgozo dolgozo = dolgozoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dolgozó nem található"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        dolgozo.setJelszo(encoder.encode(ujJelszo));

        return dolgozoMapper.toDto(dolgozoRepository.save(dolgozo));
    }

    @Override
    public void torolDolgozo(Long id) {
        dolgozoRepository.deleteById(id);
    }

    @Override
    public List<DolgozoDTO> osszesDolgozo() {
        return dolgozoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(dolgozoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DolgozoDTO getDolgozoById(Long id) {
        return dolgozoRepository.findById(id)
                .map(dolgozoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("A dolgozó nem található!"));
    }
}
