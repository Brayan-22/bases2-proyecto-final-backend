package dev.alejandro.sedeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaResponseDto {
    private Long codAsignatura;
    private String nombreAsignatura;
    private Short horasSemAsignatura;
    private Short estudMaxAsignatura;
    private Short semestreAsignatura;
    private String nombrePregrado;
}
