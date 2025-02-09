package dev.alejandro.sedeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "clasificacion")
public class Clasificacion {
    @Id
    @Size(max = 32)
    @Column(name = "nom_clasificacion", nullable = false, length = 32)
    private String nomClasificacion;

    @NotNull
    @Column(name = "max_horas_clas", nullable = false)
    private Short maxHorasClas;

    @NotNull
    @Column(name = "sueldo_clasificacion", nullable = false, precision = 10, scale = 2)
    private BigDecimal sueldoClasificacion;

}