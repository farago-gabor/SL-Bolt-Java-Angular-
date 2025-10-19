package hu.projekt.bolt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NapIdopontDTO {
    private String nap;       // pl.: "hetfo"
    private String idopont;   // pl.: "07:00"
}
