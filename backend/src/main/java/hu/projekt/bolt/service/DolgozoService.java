package hu.projekt.bolt.service;

import hu.projekt.bolt.dto.DolgozoDTO;
import java.util.List;

public interface DolgozoService {
    DolgozoDTO ujDolgozo(String nev, String email, String jelszo);
    DolgozoDTO modositDolgozo(Long id, String nev, String email, String szerepkor);
    DolgozoDTO modositJelszo(Long id, String ujJelszo);
    void torolDolgozo(Long id);
    List<DolgozoDTO> osszesDolgozo();
    DolgozoDTO getDolgozoById(Long id);
}