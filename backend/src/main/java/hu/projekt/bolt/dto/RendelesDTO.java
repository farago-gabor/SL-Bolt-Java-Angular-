package hu.projekt.bolt.dto;

import hu.projekt.bolt.model.Dolgozo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendelesDTO {

    private int id;

    private String email;
    private String telefonszam;
    private LocalDate hatarido;

    private Boolean beerkezett = false;
    private Boolean felreteve = false;
    private Boolean szoltam = false;
    private Boolean elvitte = false;

    private String dolgozoNev;
    private LocalDate felvetelDatum;

    private List<RendelesTetelDTO> tetelek;
}
