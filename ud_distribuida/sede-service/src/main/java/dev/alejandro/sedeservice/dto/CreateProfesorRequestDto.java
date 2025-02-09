package dev.alejandro.sedeservice.dto;

import dev.alejandro.sedeservice.entity.ClasificacionEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProfesorRequestDto {
    @Pattern(regexp = "\\d{10}$")
    @NotBlank
    private String docProfesor;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String direccion;
    @Email
    private String correoPersonal;
    @Pattern(regexp = "\\d{10}$")
    private String telefono;
    @NotNull
    private ClasificacionEnum clasificacion;

    @Positive
    private Integer codPregrado;
}
