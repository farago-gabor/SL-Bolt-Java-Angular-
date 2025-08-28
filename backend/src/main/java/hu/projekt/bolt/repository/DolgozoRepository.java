package hu.projekt.bolt.repository;

import hu.projekt.bolt.model.Dolgozo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DolgozoRepository extends JpaRepository<Dolgozo, Long> {
    Optional<Dolgozo> findByEmail(String email);
}
