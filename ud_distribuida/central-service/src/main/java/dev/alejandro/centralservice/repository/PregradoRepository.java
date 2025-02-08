package dev.alejandro.centralservice.repository;

import dev.alejandro.centralservice.entity.Pregrado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PregradoRepository extends JpaRepository<Pregrado,Integer> {

    Optional<Pregrado> findByNombreContainsIgnoreCase(String nombre);
}