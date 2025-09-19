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
@AllArgsConstructor
@NoArgsConstructor
public class TevekenysegGyakorisag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tevekenyseg_id")
    private Tevekenyseg tevekenyseg;

    @Enumerated(EnumType.STRING)
    private Gyakorisag gyakorisag;

    @ElementCollection
    @CollectionTable(name = "tevekenyseg_gyakorisag_napok", joinColumns = @JoinColumn(name = "gyakorisag_id"))
    @Column(name = "nap")
    private List<String> napok;

    private String idoPont;
    private LocalDate kezdoDatum;

    /*
        id INT AUTO_INCREMENT PRIMARY KEY,
	    tevekenyseg_id INT,               -- A tevékenység azonosítója
	    gyakorisag ENUM('heti', 'ketheti', 'haromheti', 'mindig', 'egyszeri'),  -- Gyakoriság típusa és ez kell-e: ('hetkoznap', 'hetvege')
	    //gyakorisag_szam INT,              -- A gyakoriság száma (pl. 2, 3 stb.) // EZ KELL-E?
	    napok VARCHAR(255),               -- Ha heti gyakoriság, akkor tároljuk, hogy melyik napokon (pl. 'hétfő, kedd')
	    ido_pontra_datum TIME,            -- Ha van pontos időpont, pl. '06:00', '09:00'
	    kezdo_datum DATE,            -- Következő esedékes végrehajtás dátumának kiszámolása // Ez csak akkor szükséges, ha 2 v 3 hetente ismétlődő
		// VAGY EZ HELYETT kezdo_datum/kezdo_het_datum és ezzel számolgatjuk az ismétlődést
	    FOREIGN KEY (tevekenyseg_id) REFERENCES tevekenysegek(id)*/

    public enum Gyakorisag {
        HETI,
        KETHETI,
        HAROMHETI,
        MINDIG,
        EGYSZERI
    }
}
