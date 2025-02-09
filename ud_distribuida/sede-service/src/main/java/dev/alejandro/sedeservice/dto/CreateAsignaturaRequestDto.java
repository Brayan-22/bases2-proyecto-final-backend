package dev.alejandro.sedeservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAsignaturaRequestDto {
    @Min(1)
    private Long codAsignatura;
    @NotBlank
    private String nombre;
    @Min(1)
    private Short horasSemanales;
    @Min(1)
    private Short maxEstudiantes;
    @Min(1)
    @Max(10)
    private Short semestre;
    @NotBlank
    private String pregrado;
}
