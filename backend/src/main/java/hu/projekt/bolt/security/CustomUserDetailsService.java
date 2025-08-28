package hu.projekt.bolt.security;

import hu.projekt.bolt.model.Dolgozo;
import hu.projekt.bolt.repository.DolgozoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

// Ez mire kell: "Spring Security requires a UserDetailsService bean to integrate with AuthenticationManager"
// Szükség van egy UserDetails objektumra, h a Spring Security felismerje
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final DolgozoRepository dolgozoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Dolgozo dolgozo = dolgozoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Nem található a dolgozó"));

        return new User(
                dolgozo.getEmail(),
                dolgozo.getJelszo(),
                List.of(new SimpleGrantedAuthority("ROLE_"+dolgozo.getSzerepkor())) // Ideiglenes szerepkör lekérés, tesztelni kell a működését
        );
    }
}
