package hu.projekt.bolt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TevekenysegNaploDTO {
    private int id;
    private String dolgozoNev;
    private String tevekenysegMegnevezes;
    private String tevekenysegLeiras;
    private LocalDate datum;
}
