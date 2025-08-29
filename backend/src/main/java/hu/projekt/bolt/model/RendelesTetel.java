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

    @EmbeddedId
    private RendelesTetelId id;

    @ManyToOne
    @MapsId("rendelesId")
    @JoinColumn(name = "rendeles_id")
    private Rendeles rendeles;

    @ManyToOne
    @MapsId("arucikkId")
    @JoinColumn(name = "arucikk_id")
    private Arucikk arucikk;

    private int mennyiseg;
    private String megjegyzes;
}
