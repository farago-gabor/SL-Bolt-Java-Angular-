package hu.projekt.bolt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tevekenyseg_naplo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TevekenysegNaplo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tevekenyseg_id")
    private Tevekenyseg tevekenyseg;

    @ManyToOne
    @JoinColumn(name = "dolgozo_id")
    private Dolgozo dolgozo;
    private LocalDate datum;

    /*
        id INT AUTO_INCREMENT PRIMARY KEY,
        tevekenyseg_id INT NOT NULL,
        dolgozo_id INT,
        datum DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        megcsinalta BOOLEAN DEFAULT FALSE, // ERRE NEMBIZTOS, HOGY SZÜKSÉG VAN
        FOREIGN KEY (tevekenyseg_id) REFERENCES tevekenysegek(id),
        FOREIGN KEY (dolgozo_id) REFERENCES dolgozok(id)
     */
}
