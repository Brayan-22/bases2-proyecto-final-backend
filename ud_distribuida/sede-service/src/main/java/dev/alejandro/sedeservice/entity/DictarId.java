package dev.alejandro.sedeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class DictarId implements Serializable {
    @Serial
    private static final long serialVersionUID = -7104520154037618898L;
    @Size(max = 10)
    @NotNull
    @Column(name = "doc_profesor", nullable = false, length = 10)
    private String docProfesor;

    @NotNull
    private GrupoId codGrupo;
}