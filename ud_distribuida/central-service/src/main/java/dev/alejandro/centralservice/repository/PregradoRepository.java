package dev.alejandro.centralservice.repository;

import dev.alejandro.centralservice.entity.Pregrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PregradoRepository extends JpaRepository<Pregrado,String> {

    Optional<Pregrado> findByNombreContainsIgnoreCase(String nombre);
}