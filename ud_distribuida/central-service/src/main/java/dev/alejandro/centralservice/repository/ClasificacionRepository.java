package dev.alejandro.centralservice.repository;

import dev.alejandro.centralservice.entity.Clasificacion;
import dev.alejandro.centralservice.entity.ClasificacionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasificacionRepository extends JpaRepository<Clasificacion, ClasificacionEnum> {
}
