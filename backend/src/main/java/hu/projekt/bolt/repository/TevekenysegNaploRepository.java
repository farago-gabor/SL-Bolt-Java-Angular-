package hu.projekt.bolt.repository;

import hu.projekt.bolt.model.Tevekenyseg;
import hu.projekt.bolt.model.TevekenysegNaplo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TevekenysegNaploRepository extends JpaRepository<TevekenysegNaplo, Integer> {

    boolean existsByTevekenysegAndDatum(Tevekenyseg tevekenyseg, LocalDate date);

    List<TevekenysegNaplo> findAllByDatum(LocalDate localDate);
}
