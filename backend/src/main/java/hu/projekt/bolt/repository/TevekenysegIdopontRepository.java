package hu.projekt.bolt.repository;

import hu.projekt.bolt.model.TevekenysegGyakorisag;
import hu.projekt.bolt.model.TevekenysegIdopont;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TevekenysegIdopontRepository extends JpaRepository<TevekenysegIdopont, Integer> {
    List<TevekenysegIdopont> findAllByGyakorisag(TevekenysegGyakorisag gyakorisag);
}
