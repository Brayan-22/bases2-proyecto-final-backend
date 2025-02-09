package dev.alejandro.sedeservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pregrado")
public class Pregrado implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cod_pregrado")
    private Integer codPregrado;

    @Size(max = 32)
    @NotNull
    @Column(name = "nombre_pregrado", nullable = false, length = 32)
    private String nombre;

    @NotNull
    @Column(name = "creditos_pregrado", nullable = false)
    private Integer creditosPregrado;

    @NotNull
    @Column(name = "nota_minima", nullable = false, precision = 3, scale = 2)
    private Double notaMinima;

    @Size(max = 64)
    @NotNull
    @Column(name = "correo_pregrado", nullable = false, length = 64)
    private String correoPregrado;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sede", nullable = false, length = 32)
    private SedeEnum sede;

    @OneToMany(mappedBy = "pregrado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Asignatura> asignaturas;
}