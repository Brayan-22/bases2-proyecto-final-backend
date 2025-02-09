package dev.alejandro.sedeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @Size(max = 10)
    @Column(name = "cod_estudiante", nullable = false, length = 10)
    private String codEstudiante;

    @Size(max = 32)
    @NotNull
    @Column(name = "nombres_estudiante", nullable = false, length = 32)
    private String nombresEstudiante;

    @Size(max = 32)
    @NotNull
    @Column(name = "apellidos_estudiante", nullable = false, length = 32)
    private String apellidosEstudiante;

    @Size(max = 64)
    @NotNull
    @Column(name = "correo_estudianti", nullable = false, length = 64)
    private String correoEstudianti;

}