package dev.alejandro.sedeservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGrupoRequestDto {
    @NotBlank
    private String codGrupo;
    @NotBlank
    private String periodoGrupo;
    @Positive
    private Long codAsignatura;
}
