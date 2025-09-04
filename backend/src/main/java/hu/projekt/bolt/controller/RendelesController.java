package hu.projekt.bolt.controller;

import hu.projekt.bolt.dto.RendelesDTO;
import hu.projekt.bolt.dto.RendelesTetelDTO;
import hu.projekt.bolt.service.RendelesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/rendelesek")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class RendelesController {
    private final RendelesServiceImpl rendelesService;

    @PostMapping
    public ResponseEntity<RendelesDTO> ujRendeles(@RequestParam String email,
                                                  @RequestParam String telefonszam,
                                                  @RequestParam String hatarido,
                                                  @RequestParam int dolgozoId,
                                                  @RequestBody List<RendelesTetelDTO> tetelek) {

        LocalDate parsedHatarido = LocalDate.parse(hatarido);

        RendelesDTO newRendeles = rendelesService.ujRendeles(email, telefonszam, parsedHatarido, dolgozoId, tetelek);
        return ResponseEntity.ok(newRendeles);
    }

    @PutMapping("/{id}/statusz")
    public ResponseEntity<RendelesDTO> modositStatusz(@PathVariable int id,
                                                      @RequestParam boolean beerkezet,
                                                      @RequestParam boolean felreteve,
                                                      @RequestParam boolean szoltam,
                                                      @RequestParam boolean elvitte) {

        RendelesDTO updatedRendeles = rendelesService.modositStatusz(id, beerkezet, felreteve, szoltam, elvitte);

        return ResponseEntity.ok(updatedRendeles);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> torolRendeles(@PathVariable int id) {

        rendelesService.torolRendeles(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<RendelesDTO>> osszesRendeles() {

        List<RendelesDTO> osszRendeles = rendelesService.osszesRendeles();

        return ResponseEntity.ok(osszRendeles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendelesDTO> getRendelesById(@PathVariable int id) {

        RendelesDTO rendeles = rendelesService.getRendelesById(id);

        return ResponseEntity.ok(rendeles);
    }

    @GetMapping("/{id}/tetelek")
    public ResponseEntity<List<RendelesTetelDTO>> getRendelesTetelek(@PathVariable int id) {

        List<RendelesTetelDTO> rendelesTetelek = rendelesService.getRendelesTetelek(id);

        return ResponseEntity.ok(rendelesTetelek);
    }

}
