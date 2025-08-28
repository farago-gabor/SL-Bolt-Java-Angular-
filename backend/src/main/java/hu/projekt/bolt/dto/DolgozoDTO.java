package hu.projekt.bolt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DolgozoDTO {
    private int id;

    private String nev;
    private String email;
    private String szerepkor;
}
