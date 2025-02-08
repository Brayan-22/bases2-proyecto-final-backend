package dev.alejandro.centralservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "nomina_profesor")
public class NominaProfesor {
    @EmbeddedId
    private NominaProfesorId id;

    @MapsId("docProfesor")
    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "doc_profesor", nullable = false)
    private Profesor profesor;

    @MapsId("idNomina")
    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_nomina", nullable = false)
    private Nomina nomina;

    @Column(name = "pago", precision = 10, scale = 2)
    private BigDecimal pago;

}