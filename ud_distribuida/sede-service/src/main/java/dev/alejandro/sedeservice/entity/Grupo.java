package dev.alejandro.sedeservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "grupo")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Grupo {
    @EmbeddedId
    private GrupoId id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "cod_asignatura", nullable = false)
    private Asignatura codAsignatura;

}