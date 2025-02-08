package dev.alejandro.centralservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PregradoResponseDto {
    private Integer codPregrado;
    private String nombrePregrado;
    private Integer creditos;
    private Double notaMinima;
    private String correo;
}
