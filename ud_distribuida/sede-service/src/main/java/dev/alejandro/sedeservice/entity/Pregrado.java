package dev.alejandro.sedeservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Pregrado")
@Table(name = "\"pregrado\"")
public class Pregrado implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cod_pregrado")
    private String codPregrado;
    @Column(name = "nombre_pregrado")
    private String nombre;
    @Column(name = "creditos_pregrado")
    private Short creditos;
    @Column(name = "nota_minima")
    private Double notaMinima;
    @Column(name = "correo_pregrado")
    private String correo;
    @Enumerated(EnumType.STRING)
    @Column(name = "sede")
    private SedeEnum sede;
    @OneToMany(mappedBy = "codPregrado",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,orphanRemoval = true)
    private Set<Asignatura> asignaturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "pregrado",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,orphanRemoval = true)
    private Set<Profesor> profesores = new LinkedHashSet<>();

}
