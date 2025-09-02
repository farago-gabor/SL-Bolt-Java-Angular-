package hu.projekt.bolt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RendelesTetelDTO {
    private int mennyiseg;
    private String megjegyzes;

    private String arucikkNev;
}
