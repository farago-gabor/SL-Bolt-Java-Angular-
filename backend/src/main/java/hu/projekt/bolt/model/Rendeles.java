package hu.projekt.bolt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rendelesek")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rendeles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String telefonszam;
    private LocalDate hatarido;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "felvette_dolgozo_id")
    private Dolgozo felvetteDolgozo;

    @Column(name = "felvetel_datum")
    private LocalDate felvetelDatum;

    public enum Status {
        NINCS,
        BEERKEZETT,
        FELRETEVE,
        SZOLTAM,
        ELVITTE
    }

}
