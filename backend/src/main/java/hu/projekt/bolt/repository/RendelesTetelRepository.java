package hu.projekt.bolt.repository;

import hu.projekt.bolt.model.RendelesTetel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendelesTetelRepository extends JpaRepository<RendelesTetel, Integer> {
    List<RendelesTetel> findByRendelesId(int rendelesId);
}
