package dev.alejandro.sedeservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "profesor")
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


    @ManyToOne(optional = false,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "nom_clasificacion", nullable = false)
    private Clasificacion clasificacion;

    @ManyToOne(optional = false,cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_pregrado")
    private Pregrado pregrado;


}