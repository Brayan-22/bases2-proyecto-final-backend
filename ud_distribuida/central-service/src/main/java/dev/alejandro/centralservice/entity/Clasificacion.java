package dev.alejandro.centralservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "clasificacion")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clasificacion {
    @Id
    @Column(name = "nom_clasificacion", nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private ClasificacionEnum nomClasificacion;

    @NotNull
    @Column(name = "max_horas_clas", nullable = false)
    private Short maxHorasClas;

    @NotNull
    @Column(name = "sueldo_clasificacion", nullable = false, precision = 10, scale = 2)
    private BigDecimal sueldoClasificacion;

}