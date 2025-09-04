package hu.projekt.bolt.controller;

import hu.projekt.bolt.dto.LoginRequest;
import hu.projekt.bolt.model.Dolgozo;
import hu.projekt.bolt.repository.DolgozoRepository;
import hu.projekt.bolt.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtil;
    private final DolgozoRepository dolgozoRepo;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getJelszo())
            );

            Dolgozo dolgozo = dolgozoRepo.findByEmail(request.getEmail()).orElseThrow();

            String token = jwtUtil.generateToken(dolgozo);

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "nev", dolgozo.getNev(),
                    "szerepkor", dolgozo.getSzerepkor()
            ));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login");
        }
    }
}
