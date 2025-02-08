package dev.alejandro.centralservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictarRequestDto {
    @NotBlank
    private String codGrupo;
    @NotBlank
    private String periodoGrupo;
    @NotBlank
    private String docProfesor;
    @Positive
    private Short horasSemana;
}
