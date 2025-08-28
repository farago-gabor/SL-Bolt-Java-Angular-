package hu.projekt.bolt.controller;

import hu.projekt.bolt.dto.DolgozoDTO;
import hu.projekt.bolt.service.DolgozoServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dolgozok")
@RequiredArgsConstructor
public class DolgozoController {

    private final DolgozoServiceImpl dolgozoService;
    // Ez az auth-ban lesz megvalósítva
    /*
    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "ADMIN".equals(role);
    }
    */
    // Új dolgozó hozzáadása (POST)
    @PostMapping
    public String ujDolgozo(@RequestParam String nev,
                            @RequestParam String email,
                            @RequestParam String jelszo,
                            HttpSession session, Model model) {
        /*
        if (!isAdmin(session)) {
            return "redirect:/"; // Redirect to login or error page
        }
         */

        DolgozoDTO newDolgozo = dolgozoService.ujDolgozo(nev, email, jelszo);
        model.addAttribute("dolgozo", newDolgozo);
        return "redirect:/admin/dolgozok";  // Redirect to the list page
    }

    // Dolgozó módosítása (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<DolgozoDTO> modositDolgozo(
            @PathVariable Long id,
            @RequestBody DolgozoDTO dolgozoDTO,
            HttpSession session) {
        /*
        if (!isAdmin(session)) {
            return ResponseEntity.status(403).build(); // Forbidden if not an admin
        }
         */

        DolgozoDTO updatedDolgozo = dolgozoService.modositDolgozo(id, dolgozoDTO.getNev(), dolgozoDTO.getEmail(), dolgozoDTO.getSzerepkor());
        return ResponseEntity.ok(updatedDolgozo);
    }

    // Jelszó módosítása (PUT)
    @PutMapping("/{id}/jelszo")
    public ResponseEntity<DolgozoDTO> modositJelszo(
            @PathVariable Long id,
            @RequestParam String ujJelszo,
            HttpSession session) {
        /*
        if (!isAdmin(session)) {
            return ResponseEntity.status(403).build(); // Forbidden if not an admin
        }
        */

        DolgozoDTO updatedDolgozo = dolgozoService.modositJelszo(id, ujJelszo);
        return ResponseEntity.ok(updatedDolgozo);
    }

    // Dolgozó törlése (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> torolDolgozo(@PathVariable Long id, HttpSession session) {
        /*
        if (!isAdmin(session)) {
            return ResponseEntity.status(403).build(); // Forbidden if not an admin
        }
        */

        dolgozoService.torolDolgozo(id);
        return ResponseEntity.noContent().build();
    }

    // Összes dolgozó lekérdezése (GET)
    @GetMapping
    public ResponseEntity<List<DolgozoDTO>> osszesDolgozo(HttpSession session) {
        /*
        if (!isAdmin(session)) {
            return ResponseEntity.status(403).build(); // Forbidden if not an admin
        }
        */

        List<DolgozoDTO> dolgozok = dolgozoService.osszesDolgozo();
        return ResponseEntity.ok(dolgozok);
    }

    // Egy dolgozó lekérdezése ID alapján (GET)
    @GetMapping("/{id}")
    public ResponseEntity<DolgozoDTO> getDolgozoById(@PathVariable Long id, HttpSession session) {
        /*
        if (!isAdmin(session)) {
            return ResponseEntity.status(403).build(); // Forbidden if not an admin
        }
        */

        DolgozoDTO dolgozo = dolgozoService.getDolgozoById(id);
        return ResponseEntity.ok(dolgozo);
    }

}
