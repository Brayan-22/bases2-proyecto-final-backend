package dev.alejandro.centralservice.dto;

import dev.alejandro.centralservice.entity.SedeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Min(1)
    private Double notaMinima;
    @Email
    private String correo;
    private SedeEnum sede;
    private List<CreateAsignaturaDto> asignaturas;
}
