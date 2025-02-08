package dev.alejandro.centralservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePagoNominaRequestDto {
    @NotBlank
    private String docProfesor;
    @Positive
    private Double pago;
    @Past
    private LocalDate fechaInicio;
    @FutureOrPresent
    private LocalDate fechaFin;
}
