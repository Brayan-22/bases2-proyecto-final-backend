package dev.alejandro.centralservice.repository;

import dev.alejandro.centralservice.entity.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AsignaturaRepository extends JpaRepository<Asignatura,Long> {
    Optional<Asignatura> findByNombreAsignaturaContainsIgnoreCase(String nombre);
}
