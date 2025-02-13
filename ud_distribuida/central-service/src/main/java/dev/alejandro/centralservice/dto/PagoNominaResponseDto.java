package dev.alejandro.centralservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoNominaResponseDto {
    private String idNomina;
    private String estado;
}
