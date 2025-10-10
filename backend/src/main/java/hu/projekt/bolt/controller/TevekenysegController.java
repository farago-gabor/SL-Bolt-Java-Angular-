package hu.projekt.bolt.controller;

import hu.projekt.bolt.dto.TevekenysegDTO;
import hu.projekt.bolt.dto.TevekenysegNaploDTO;
import hu.projekt.bolt.service.TevekenysegServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/tevekenysegek")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TevekenysegController {
    private final TevekenysegServiceImpl tevekenysegService;

    // 1.a - Mai elvégzendő feladatok
    @GetMapping("/ma/elvegzendo")
    public ResponseEntity<List<TevekenysegDTO>> getMaiElvegzendoFeladatok() {
        List<TevekenysegDTO> feladatok = tevekenysegService.maiElvegzendoFeladatok();
        return ResponseEntity.ok(feladatok);
    }

    // 1.b - Feladat elvégzése
    @PostMapping("/elvegzes")
    public ResponseEntity<Void> feladatElvegzese(@RequestParam int tevekenysegId,
                                                 @RequestParam int dolgozoId,
                                                 @RequestParam String datum) {
        LocalDate parsedDatum = LocalDate.parse(datum);
        tevekenysegService.feladatElvegzese(tevekenysegId, dolgozoId, parsedDatum);
        return ResponseEntity.ok().build();
    }

    // 2.a - Mai elvégzett feladatok
    @GetMapping("/ma/elvegzettek")
    public ResponseEntity<List<TevekenysegNaploDTO>> getMaiElvegzettFeladatok() {
        List<TevekenysegNaploDTO> naplo = tevekenysegService.maiElvegzettFeladatok();
        return ResponseEntity.ok(naplo);
    }

    // 3.a - Összes naplóbejegyzés
    @GetMapping("/naplo")
    public ResponseEntity<List<TevekenysegNaploDTO>> getOsszesNaplobejegyzes() {
        List<TevekenysegNaploDTO> naplo = tevekenysegService.osszesNaplobejegyzes();
        return ResponseEntity.ok(naplo);
    }

    // 4.a - Új tevékenység létrehozása
    @PostMapping
    public ResponseEntity<Void> ujTevekenyseg(@RequestBody TevekenysegDTO dto) {
        tevekenysegService.ujTevekenyseg(dto);
        return ResponseEntity.ok().build();
    }

    // 4.a - Összes tevékenység lekérdezése
    @GetMapping
    public ResponseEntity<List<TevekenysegDTO>> getOsszesTevekenyseg() {
        List<TevekenysegDTO> tevekenysegek = tevekenysegService.osszesTevekenyseg();
        return ResponseEntity.ok(tevekenysegek);
    }

    // 4.a - Tevékenység módosítása
    @PutMapping("/{id}")
    public ResponseEntity<Void> modositTevekenyseg(@PathVariable int id,
                                                   @RequestBody TevekenysegDTO dto) {
        tevekenysegService.modositTevekenyseg(id, dto);
        return ResponseEntity.ok().build();
    }

    // 4.a - Tevékenység törlése
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> torolTevekenyseg(@PathVariable int id) {
        tevekenysegService.torolTevekenyseg(id);
        return ResponseEntity.noContent().build();
    }

}
