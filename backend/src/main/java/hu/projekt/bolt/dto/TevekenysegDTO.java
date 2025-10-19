package hu.projekt.bolt.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TevekenysegDTO {

    private String megnevezes;
    private String leiras;
    private String gyakorisag;
    private LocalDate kezdoDatum;
    private List<NapIdopontDTO> idopontok;

}
