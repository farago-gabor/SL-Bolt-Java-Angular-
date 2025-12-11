package hu.projekt.bolt.repository;

import hu.projekt.bolt.model.Tevekenyseg;
import hu.projekt.bolt.model.TevekenysegIdopont;
import hu.projekt.bolt.model.TevekenysegNaplo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TevekenysegNaploRepository extends JpaRepository<TevekenysegNaplo, Integer> {

    boolean existsByTevekenysegAndIdopontAndDatum(Tevekenyseg tevekenyseg, TevekenysegIdopont idopont, LocalDate date);

    List<TevekenysegNaplo> findAllByDatum(LocalDate localDate);

    Page<TevekenysegNaplo> findAllByOrderByDatumDesc(Pageable pageable);
    Page<TevekenysegNaplo> findAllByDatum(LocalDate datum, Pageable pageable);
}
