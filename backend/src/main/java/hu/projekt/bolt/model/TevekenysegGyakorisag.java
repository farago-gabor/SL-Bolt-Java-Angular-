package hu.projekt.bolt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tevekenyseg_gyakorisag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TevekenysegGyakorisag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tevekenyseg_id")
    private Tevekenyseg tevekenyseg;

    @Enumerated(EnumType.STRING)
    private Gyakorisag gyakorisag;

    private LocalDate kezdoDatum;

    @OneToMany(mappedBy = "gyakorisag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TevekenysegIdopont> idopontok;

    public enum Gyakorisag {
        HETI,
        KETHETI,
        HAROMHETI,
        MINDIG,
        EGYSZERI
    }
}
