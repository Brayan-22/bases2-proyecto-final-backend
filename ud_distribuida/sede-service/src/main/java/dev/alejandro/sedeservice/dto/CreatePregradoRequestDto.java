package dev.alejandro.sedeservice.dto;

import dev.alejandro.sedeservice.entity.SedeEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePregradoRequestDto {
    private String codPregrado;
    @Size(max = 32)
    @NotBlank
    private String nombre;
    @Min(1)
    private Short creditos;
    @Positive
    private Double notaMinima;
    @Email
    private String correo;
    private SedeEnum sede;
    private List<CreateAsignaturaDto> asignaturas;
}
