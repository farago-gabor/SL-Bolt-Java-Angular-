package hu.projekt.bolt.dto;

import hu.projekt.bolt.model.Rendeles;
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

    private Rendeles.Status status;

    private String dolgozoNev;
    private LocalDate felvetelDatum;

    private List<RendelesTetelDTO> tetelek;
}
