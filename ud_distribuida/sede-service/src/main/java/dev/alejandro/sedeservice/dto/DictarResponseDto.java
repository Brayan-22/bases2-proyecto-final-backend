package dev.alejandro.sedeservice.dto;

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
