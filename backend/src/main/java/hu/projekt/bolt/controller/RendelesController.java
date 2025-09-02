package hu.projekt.bolt.controller;

import hu.projekt.bolt.dto.RendelesDTO;
import hu.projekt.bolt.dto.RendelesTetelDTO;
import hu.projekt.bolt.service.RendelesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/rendelesek")
@RequiredArgsConstructor
public class RendelesController {
    private final RendelesServiceImpl rendelesService;

//     MEGVALÓSITANDÓ:
//    RendelesDTO ujRendeles(String email, String telefonszam, LocalDate hatarido, int dolgozoId, List<RendelesTetelDTO> tetelek);
//    RendelesDTO modositStatusz(int id, boolean beerkezet, boolean felreteve, boolean szoltam, boolean elvitte);
//    void torolRendeles(int id);
//    List<RendelesDTO> osszesRendeles();
//    List<RendelesTetelDTO> getRendelesTetelek(int rendelesId);
//    RendelesDTO getRendelesById(int id);

}
