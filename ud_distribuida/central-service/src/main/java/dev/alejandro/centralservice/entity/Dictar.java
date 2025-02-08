package dev.alejandro.centralservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "dictar")
public class Dictar {
    @EmbeddedId
    private DictarId id;

    @MapsId("docProfesor")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "doc_profesor", nullable = false)
    private Profesor docProfesor;

    @MapsId("codGrupo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cod_grupo", referencedColumnName = "cod_grupo")
    @JoinColumn(name = "periodo_grupo", referencedColumnName = "periodo_grupo")
    private Grupo grupo;


    @NotNull
    @Column(name = "hora_ssem_dictar", nullable = false)
    private Short horaSemDictar;

}