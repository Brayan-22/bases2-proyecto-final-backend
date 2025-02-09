package dev.alejandro.sedeservice.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
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
