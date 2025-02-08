package dev.alejandro.centralservice.entity;

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
public class NominaProfesorId implements Serializable {
    private static final long serialVersionUID = 3807981870114547508L;
    @Size(max = 10)
    @NotNull
    @Column(name = "doc_profesor", nullable = false, length = 10)
    private String docProfesor;

    @Size(max = 32)
    @NotNull
    @Column(name = "id_nomina", nullable = false, length = 32)
    private String idNomina;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NominaProfesorId entity = (NominaProfesorId) o;
        return Objects.equals(this.docProfesor, entity.docProfesor) &&
                Objects.equals(this.idNomina, entity.idNomina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docProfesor, idNomina);
    }

}