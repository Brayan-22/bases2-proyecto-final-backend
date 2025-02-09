package dev.alejandro.sedeservice.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "calificar")
public class Calificar {
    @EmbeddedId
    private CalificarId id;


}