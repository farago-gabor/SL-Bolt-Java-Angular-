package hu.projekt.bolt.service;

import hu.projekt.bolt.dto.DolgozoDTO;
import java.util.List;

public interface DolgozoService {
    DolgozoDTO ujDolgozo(String nev, String email, String jelszo);
    DolgozoDTO modositDolgozo(int id, String nev, String email, String szerepkor);
    DolgozoDTO modositJelszo(int id, String ujJelszo);
    void torolDolgozo(int id);
    List<DolgozoDTO> osszesDolgozo();
    DolgozoDTO getDolgozoById(int id);
}