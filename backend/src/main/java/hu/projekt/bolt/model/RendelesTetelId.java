package hu.projekt.bolt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RendelesTetelId implements Serializable {
    @Column(name = "rendeles_id")
    private int rendelesId;
    @Column(name = "arucikk_id")
    private int arucikkId;
}