package dev.alejandro.centralservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorResponseDto {
    private String docProfesor;
    private String nombre;
    private String apellido;
    private String correoInstitucional;
}
