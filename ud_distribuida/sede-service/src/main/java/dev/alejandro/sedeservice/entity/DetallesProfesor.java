package dev.alejandro.sedeservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "detalles_profesor")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetallesProfesor {
    @Id
    private String docProfesor;

    @OneToOne
    @MapsId("docProfesor")
    @JoinColumn(name = "doc_profesor")
    private Profesor profesor;

    @Size(max = 32)
    @NotNull
    @Column(name = "direccion_profesor", nullable = false, length = 32)
    private String direccionProfesor;

    @Size(max = 64)
    @NotNull
    @Column(name = "correo_personar", nullable = false, length = 64)
    private String correoPersonal;

    @Size(max = 10)
    @NotNull
    @Column(name = "telefono_profesor", nullable = false, length = 10)
    private String telefonoProfesor;

}