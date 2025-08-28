package hu.projekt.bolt.security;

// Meghatározza:
// - melyik endpointok-hoz kell authentikáció
// - melyik elérhető publikusan
// - hogyan is authentikálunk (email, jelszó)
// - milyen tokent használunk
// - jelszó titkosítás

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

}
