package hu.projekt.bolt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NapIdopontDTO {
    private int id;
    private String nap;       // pl.: "hetfo"
    private LocalTime idopont;   // pl.: "07:00"
}
