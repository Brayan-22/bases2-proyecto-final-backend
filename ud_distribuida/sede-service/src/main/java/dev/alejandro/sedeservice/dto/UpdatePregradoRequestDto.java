package dev.alejandro.sedeservice.dto;

import dev.alejandro.sedeservice.entity.SedeEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePregradoRequestDto {
    private String codPregrado;
    @NotBlank
    private String nombre;
    @Min(1)
    private Short creditos;
    @Positive
    private Double notaMinima;
    @Email
    private String correo;
    @NotNull
    private SedeEnum sede;
}
