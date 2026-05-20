package hu.projekt.bolt.service;

import hu.projekt.bolt.dto.RendelesDTO;
import hu.projekt.bolt.dto.RendelesTetelDTO;
import hu.projekt.bolt.model.Rendeles;

import java.time.LocalDate;
import java.util.List;

public interface RendelesService {
    RendelesDTO ujRendeles(String email, String telefonszam, LocalDate hatarido, int dolgozoId, List<RendelesTetelDTO> tetelek);
    RendelesDTO modositStatusz(int id, Rendeles.Status status);
    void torolRendeles(int id);
    List<RendelesDTO> osszesRendeles();
    List<RendelesTetelDTO> getRendelesTetelek(int rendelesId);
    RendelesDTO getRendelesById(int id);
}
