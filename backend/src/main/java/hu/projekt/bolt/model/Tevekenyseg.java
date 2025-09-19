package hu.projekt.bolt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tevekenysegek")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tevekenyseg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String megnevezes;
    private String leiras;

    /*
    megnevezes VARCHAR(100) NOT NULL,  -- Tevékenység neve
    !EZT NEM KELL rendszeres BOOLEAN DEFAULT FALSE,  -- Rendszeresség: van-e meghatározott időpontja?
    reszletes_leiras TEXT             -- Részletes leírás a tevékenységről, ha szükséges
    */
}
