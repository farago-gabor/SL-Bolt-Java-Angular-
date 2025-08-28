package hu.projekt.bolt.security;

// Generálja és validálja JWT-t

import hu.projekt.bolt.model.Dolgozo;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final String JWT_KULCS = "HosszabbKulcsKellHogyNeKapjakErrort";
    private final long jwtLejarat = 86400000; // A kulcs lejáratának ideje (1 nap)

    public String generateToken(Dolgozo dolgozo) {
        return Jwts.builder()
                .setSubject(dolgozo.getEmail())
                .claim("nev", dolgozo.getNev())
                .claim("szerepkor", dolgozo.getSzerepkor())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtLejarat))
                .signWith(Keys.hmacShaKeyFor(JWT_KULCS.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(JWT_KULCS.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(JWT_KULCS.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }


}
