package dev.alejandro.centralservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictarResponseDto {
    private String codGrupo;
    private String periodoGrupo;
    private String asignatura;
    private String correoProfesor;
}
