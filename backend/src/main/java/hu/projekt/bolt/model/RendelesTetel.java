package hu.projekt.bolt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rendeles_tetelek")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RendelesTetel {

    @Id
    @ManyToOne
    @JoinColumn(name = "rendeles_id")
    private Rendeles rendeles;

    @Id
    @ManyToOne
    @JoinColumn(name = "arucikk_id")
    private Arucikk arucikk;

    private int mennyiseg;
    private String megjegyzes;
}
