package hu.projekt.bolt.repository;

import hu.projekt.bolt.model.Arucikk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArucikkRepository extends JpaRepository<Arucikk, Integer> {
    Optional<Arucikk> findByMegnevezes(String megnevezes);
}
