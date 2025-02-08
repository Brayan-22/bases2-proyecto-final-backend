package dev.alejandro.centralservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "profesor")
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {
    @Id
    @Size(max = 10)
    @Column(name = "doc_profesor", nullable = false, length = 10)
    private String docProfesor;

    @Size(max = 32)
    @NotNull
    @Column(name = "nombre_profesor", nullable = false, length = 32)
    private String nombreProfesor;

    @Size(max = 32)
    @NotNull
    @Column(name = "apellido_profesor", nullable = false, length = 32)
    private String apellidoProfesor;

    @Size(max = 64)
    @NotNull
    @Column(name = "correo_profesor", nullable = false, length = 64)
    private String correoProfesor;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "profesor")
    private DetallesProfesor detallesProfesor;

    @OneToMany(mappedBy = "profesor")
    private Set<NominaProfesor> nominaProfesors = new LinkedHashSet<>();

    @ManyToOne(optional = false,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "nom_clasificacion", nullable = false)
    private Clasificacion clasificacion;

    @ManyToOne(optional = false,cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_pregrado")
    private Pregrado pregrado;

}