package dev.alejandro.centralservice.dto;

import dev.alejandro.centralservice.entity.SedeEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePregradoRequestDto {

    @Size(min = 36, max = 36)
    private String codPregrado;
    @NotBlank
    private String nombre;
    @Min(1)
    private Short creditos;
    @Min(1)
    private Double notaMinima;
    @Email
    private String correo;
    @NotNull
    private SedeEnum sede;
}
