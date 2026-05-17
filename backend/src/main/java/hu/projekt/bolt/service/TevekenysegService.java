package hu.projekt.bolt.service;

import hu.projekt.bolt.dto.TevekenysegDTO;
import hu.projekt.bolt.dto.TevekenysegNaploDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface TevekenysegService {

    // 1.a - Mai elvégzendő feladatok lekérése
    List<TevekenysegDTO> maiElvegzendoFeladatok();

    // 1.b - Feladat elvégzése (naplózás)
    void feladatElvegzese(int tevekenysegId, int idopontId, int dolgozoId, LocalDate datum);

    // 2.a - Mai elvégzett feladatok lekérése
    List<TevekenysegNaploDTO> maiElvegzettFeladatok();

    // 3.a - Naplózott tevékenységek lekérése (összes)
    Page<TevekenysegNaploDTO> osszesNaplobejegyzes(Pageable pageable);

    // 4.a - Tevékenység hozzáadása (gyakorisággal együtt)
    void ujTevekenyseg(TevekenysegDTO dto);

    // 4.b - Összes tevékenység lekérése, csoportosítva gyakoriság szerint
    // Pl. HETI, KETHETI stb.
    List<TevekenysegDTO> osszesTevekenyseg();

    // 4.c - Tevékenység módosítása (azonosító alapján)
    void modositTevekenyseg(int tevekenysegId, TevekenysegDTO dto);

    // 4.d - Tevékenység törlése
    void torolTevekenyseg(int tevekenysegId);

}
