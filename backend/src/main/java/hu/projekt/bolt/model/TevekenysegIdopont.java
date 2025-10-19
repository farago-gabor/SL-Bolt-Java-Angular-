package hu.projekt.bolt.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tevekenyseg_idopontok")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TevekenysegIdopont {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "gyakorisag_id")
    private TevekenysegGyakorisag gyakorisag;

    private String nap;        // például: "hetfo", "kedd", stb.

    private String idopont;    // például: "07:00", "14:00"
}