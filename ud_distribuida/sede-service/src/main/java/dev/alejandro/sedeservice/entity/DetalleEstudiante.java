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
@Table(name = "detalle_estudiante")
public class DetalleEstudiante {
    @Id
    @Size(max = 10)
    @Column(name = "cod_estudiante", nullable = false, length = 10)
    private String codEstudiante;

    @Size(max = 10)
    @NotNull
    @Column(name = "telefono_estudiante", nullable = false, length = 10)
    private String telefonoEstudiante;

    @Size(max = 64)
    @NotNull
    @Column(name = "correo_personal_estudiante", nullable = false, length = 64)
    private String correoPersonalEstudiante;

    @Size(max = 10)
    @NotNull
    @Column(name = "num_documento", nullable = false, length = 10)
    private String numDocumento;

}