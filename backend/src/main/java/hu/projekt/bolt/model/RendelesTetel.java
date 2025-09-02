package hu.projekt.bolt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
class RendelesTetelId implements Serializable {
    @Column(name = "rendeles_id")
    private int rendelesId;
    @Column(name = "arucikk_id")
    private int arucikkId;
}

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
