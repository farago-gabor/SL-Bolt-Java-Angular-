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
    private TevekenysegDTO tevekenyseg;
    private DolgozoDTO dolgozoId;
    private LocalDate datum;
}
