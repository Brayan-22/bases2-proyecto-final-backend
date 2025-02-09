package dev.alejandro.sedeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class GrupoId implements Serializable {
    private static final long serialVersionUID = 3344875604694507072L;
    @Size(max = 10)
    @NotNull
    @Column(name = "cod_grupo", nullable = false, length = 10)
    private String codGrupo;

    @Size(max = 10)
    @NotNull
    @Column(name = "periodo_grupo", nullable = false, length = 10)
    private String periodoGrupo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GrupoId entity = (GrupoId) o;
        return Objects.equals(this.periodoGrupo, entity.periodoGrupo) &&
                Objects.equals(this.codGrupo, entity.codGrupo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periodoGrupo, codGrupo);
    }

}