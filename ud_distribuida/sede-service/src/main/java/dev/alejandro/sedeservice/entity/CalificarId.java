package dev.alejandro.sedeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CalificarId implements Serializable {
    private static final long serialVersionUID = -175405114611933531L;
    @Size(max = 10)
    @NotNull
    @Column(name = "cod_grupo", nullable = false, length = 10)
    private String codGrupo;

    @Size(max = 10)
    @NotNull
    @Column(name = "periodo_grupo", nullable = false, length = 10)
    private String periodoGrupo;

    @Size(max = 10)
    @NotNull
    @Column(name = "cod_estudiante", nullable = false, length = 10)
    private String codEstudiante;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CalificarId entity = (CalificarId) o;
        return Objects.equals(this.codEstudiante, entity.codEstudiante) &&
                Objects.equals(this.periodoGrupo, entity.periodoGrupo) &&
                Objects.equals(this.codGrupo, entity.codGrupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codEstudiante, periodoGrupo, codGrupo);
    }

}