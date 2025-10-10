package hu.projekt.bolt.repository;

import hu.projekt.bolt.model.Tevekenyseg;
import hu.projekt.bolt.model.TevekenysegGyakorisag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TevekenysegGyakorisagRepository extends JpaRepository<TevekenysegGyakorisag, Integer> {

    Optional<TevekenysegGyakorisag> findByTevekenyseg(Tevekenyseg t);
    void deleteByTevekenyseg(Tevekenyseg t);
}
