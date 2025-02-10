package dev.alejandro.centralservice.dto;

import dev.alejandro.centralservice.entity.ClasificacionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorResponseDto {
    private String docProfesor;
    private String nombre;
    private String apellido;
    private String correoProfesor;
    private ClasificacionEnum clasificacion;
    private String codPregrado;
}
