package dev.alejandro.sedeservice.repository;

import dev.alejandro.sedeservice.entity.Pregrado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PregradoRepository extends JpaRepository<Pregrado, String> {
  Optional<Pregrado> findByNombreContainsIgnoreCase(String nombre);
}