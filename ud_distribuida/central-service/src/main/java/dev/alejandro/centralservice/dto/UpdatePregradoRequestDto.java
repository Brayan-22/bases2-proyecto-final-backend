package dev.alejandro.centralservice.dto;

import dev.alejandro.centralservice.entity.SedeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePregradoRequestDto {
    @Min(1)
    private Integer codPregrado;
    @NotBlank
    private String nombre;
    @Min(1)
    private Integer creditos;
    @Min(1)
    private Double notaMinima;
    @Email
    private String correo;
    @NotNull
    private SedeEnum sede;
}
