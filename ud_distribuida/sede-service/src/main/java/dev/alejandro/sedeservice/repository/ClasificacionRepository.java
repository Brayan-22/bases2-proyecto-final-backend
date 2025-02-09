package dev.alejandro.sedeservice.repository;

import dev.alejandro.sedeservice.entity.Clasificacion;
import dev.alejandro.sedeservice.entity.ClasificacionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasificacionRepository extends JpaRepository<Clasificacion, ClasificacionEnum> {
}
