package hu.projekt.bolt.service;

import hu.projekt.bolt.dto.TevekenysegDTO;
import hu.projekt.bolt.dto.TevekenysegNaploDTO;

import java.time.LocalDate;
import java.util.List;

public interface TevekenysegService {

    // 1.a - Mai elvégzendő feladatok lekérése
    List<TevekenysegDTO> maiElvegzendoFeladatok();

    // 1.b - Feladat elvégzése (naplózás)
    void feladatElvegzese(int tevekenysegId, int dolgozoId, LocalDate datum);

    // 2.a - Mai elvégzett feladatok lekérése
    List<TevekenysegNaploDTO> maiElvegzettFeladatok();

    // 3.a - Naplózott tevékenységek lekérése (összes)
    List<TevekenysegNaploDTO> osszesNaplobejegyzes();

    // 4.a - Tevékenység hozzáadása (gyakorisággal együtt)
    void ujTevekenyseg(TevekenysegDTO dto);

    // 4.b - Összes tevékenység lekérése, csoportosítva gyakoriság szerint
    // Pl. HETI, KETHETI stb.
    List<TevekenysegDTO> osszesTevekenyseg();

    // 4.c - Tevékenység módosítása (azonosító alapján)
    void modositTevekenyseg(int tevekenysegId, TevekenysegDTO dto);

    // 4.d - Tevékenység törlése
    void torolTevekenyseg(int tevekenysegId);

    /*
        DolgozoDTO ujDolgozo(String nev, String email, String jelszo);
        DolgozoDTO modositDolgozo(int id, String nev, String email, String szerepkor);
        DolgozoDTO modositJelszo(int id, String ujJelszo);
        void torolDolgozo(int id);
    */
}
