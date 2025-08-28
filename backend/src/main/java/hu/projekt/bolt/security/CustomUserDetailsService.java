package hu.projekt.bolt.security;

import org.springframework.context.annotation.Configuration;

// Ez mire kell: "Spring Security requires a UserDetailsService bean to integrate with AuthenticationManager"
// Szükség van egy UserDetails objektumra, h a Spring Security felismerje
@Configuration
public class CustomUserDetailsService {
}
