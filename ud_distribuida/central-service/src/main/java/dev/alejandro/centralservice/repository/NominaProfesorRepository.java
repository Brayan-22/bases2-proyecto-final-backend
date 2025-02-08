package dev.alejandro.centralservice.repository;

import dev.alejandro.centralservice.entity.NominaProfesor;
import dev.alejandro.centralservice.entity.NominaProfesorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NominaProfesorRepository extends JpaRepository<NominaProfesor, NominaProfesorId> {
}