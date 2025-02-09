package dev.alejandro.sedeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class DictarId implements Serializable {
    private static final long serialVersionUID = 5176909892148268910L;
    @Size(max = 10)
    @NotNull
    @Column(name = "doc_profesor", nullable = false, length = 10)
    private String docProfesor;

    @NotNull
    private GrupoId codGrupo;

}