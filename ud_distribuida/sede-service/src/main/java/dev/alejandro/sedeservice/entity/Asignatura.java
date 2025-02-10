package dev.alejandro.sedeservice.entity;

import dev.alejandro.sedeservice.entity.Pregrado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "asignatura")
public class Asignatura {
    @Id
    @Column(name = "cod_asignatura", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "cod_pregrado", nullable = false)
    private Pregrado codPregrado;

    @Size(max = 32)
    @NotNull
    @Column(name = "nombre_asignatura", nullable = false, length = 32)
    private String nombreAsignatura;

    @NotNull
    @Column(name = "horas_sem_asignatura", nullable = false)
    private Short horasSemAsignatura;

    @NotNull
    @Column(name = "estud_max_asignatura", nullable = false)
    private Short estudMaxAsignatura;

    @NotNull
    @Column(name = "semestre_asignatura", nullable = false)
    private Short semestreAsignatura;


}