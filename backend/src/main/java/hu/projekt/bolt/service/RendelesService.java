package hu.projekt.bolt.service;

import hu.projekt.bolt.dto.RendelesDTO;
import hu.projekt.bolt.dto.RendelesTetelDTO;

import java.time.LocalDate;
import java.util.List;

public interface RendelesService {
    // ujRendeles, modositStatusz, modositRendeles, torolRendeles, osszesRendeles, getRendelesTetelek , getRendelesById

    RendelesDTO ujRendeles(String email, String telefonszam, LocalDate hatarido, int dolgozoId, List<RendelesTetelDTO> tetelek);
    RendelesDTO modositStatusz(int id, boolean beerkezet, boolean felreteve, boolean szoltam, boolean elvitte);
    void torolRendeles(int id);
    List<RendelesDTO> osszesRendeles();
    List<RendelesTetelDTO> getRendelesTetelek(int rendelesId);
    RendelesDTO getRendelesById(int id);

    // EXTRA funkció, ha odajutok: lehessen szerkeszteni is az adott rendelés információit, ne csak a státuszát
//    RendelesDTO modositRendeles(String email, String telefonszam, LocalDate hatarido, int dolgozoId, List<RendelesTetelDTO> tetelek);

}
