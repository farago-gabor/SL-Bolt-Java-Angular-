package hu.projekt.bolt.repository;

import hu.projekt.bolt.model.Rendeles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RendelesRepository extends JpaRepository<Rendeles, Integer> {
    Optional<Rendeles> findById(Integer integer);
}
