package dev.alejandro.centralservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "nomina")
public class Nomina {
    @Id
    @Size(max = 36)
    @Column(name = "id_nomina", nullable = false, length = 36)
    private String idNomina;

    @NotNull
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @NotNull
    @Column(name = "estado", nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private EstadoPagoEnum estado;

    @OneToMany(mappedBy = "nomina")
    private Set<NominaProfesor> nominaProfesors = new LinkedHashSet<>();

}