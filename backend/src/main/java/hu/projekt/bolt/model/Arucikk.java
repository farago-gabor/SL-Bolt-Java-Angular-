package hu.projekt.bolt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "arucikkek")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Arucikk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cikkcsoport;
    private String megnevezes;
    private String vonalkod;
}
