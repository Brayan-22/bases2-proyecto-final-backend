package dev.alejandro.sedeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PregradoResponseDto {
    private String codPregrado;
    private String nombrePregrado;
    private Short creditos;
    private Double notaMinima;
    private String correo;
}
