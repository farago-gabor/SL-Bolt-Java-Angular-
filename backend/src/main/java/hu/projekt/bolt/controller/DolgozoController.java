package hu.projekt.bolt.controller;

import hu.projekt.bolt.dto.DolgozoDTO;
import hu.projekt.bolt.mapper.DolgozoMapper;
import hu.projekt.bolt.model.Dolgozo;
import hu.projekt.bolt.service.DolgozoServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dolgozok")
@RequiredArgsConstructor
public class DolgozoController {

    private final DolgozoServiceImpl dolgozoService;
    private final DolgozoMapper dolgozoMapper;

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_admin"));
    }
    // Új dolgozó hozzáadása (POST)
    @PostMapping
    public ResponseEntity<DolgozoDTO> ujDolgozo(@RequestParam String nev,
                                                @RequestParam String email,
                                                @RequestParam String jelszo) {
        if (!isAdmin()) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        DolgozoDTO newDolgozo = dolgozoService.ujDolgozo(nev, email, jelszo);
        return ResponseEntity.ok(newDolgozo);
    }

    // Dolgozó módosítása (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<DolgozoDTO> modositDolgozo(
            @PathVariable Long id,
            @RequestBody DolgozoDTO dolgozoDTO) {

        if (!isAdmin()) {
            return ResponseEntity.status(403).build();
        }

        DolgozoDTO updatedDolgozo = dolgozoService.modositDolgozo(id, dolgozoDTO.getNev(), dolgozoDTO.getEmail(), dolgozoDTO.getSzerepkor());
        return ResponseEntity.ok(updatedDolgozo);
    }

    // Jelszó módosítása (PUT)
    @PutMapping("/{id}/jelszo")
    public ResponseEntity<DolgozoDTO> modositJelszo(
            @PathVariable Long id,
            @RequestParam String ujJelszo) {

        if (!isAdmin()) {
            return ResponseEntity.status(403).build();
        }

        DolgozoDTO updatedDolgozo = dolgozoService.modositJelszo(id, ujJelszo);
        return ResponseEntity.ok(updatedDolgozo);
    }

    // Dolgozó törlése (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> torolDolgozo(@PathVariable Long id) {

        if (!isAdmin()) {
            return ResponseEntity.status(403).build();
        }

        dolgozoService.torolDolgozo(id);
        return ResponseEntity.noContent().build();
    }

    // Összes dolgozó lekérdezése (GET)
    @GetMapping
    public ResponseEntity<List<DolgozoDTO>> osszesDolgozo() {

        if (!isAdmin()) {
            return ResponseEntity.status(403).build();
        }

        List<DolgozoDTO> dolgozok = dolgozoService.osszesDolgozo();
        return ResponseEntity.ok(dolgozok);
    }

    // Egy dolgozó lekérdezése ID alapján (GET)
    @GetMapping("/{id}")
    public ResponseEntity<DolgozoDTO> getDolgozoById(@PathVariable Long id) {

        if (!isAdmin()) {
            return ResponseEntity.status(403).build();
        }

        DolgozoDTO dolgozo = dolgozoService.getDolgozoById(id);
        return ResponseEntity.ok(dolgozo);
    }

}
