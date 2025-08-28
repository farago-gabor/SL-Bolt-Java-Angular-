package hu.projekt.bolt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dolgozok")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dolgozo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nev;
    private String email;
    private String jelszo;
    private String szerepkor;
}
