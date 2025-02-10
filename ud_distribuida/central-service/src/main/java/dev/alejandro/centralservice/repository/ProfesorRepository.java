package dev.alejandro.centralservice.repository;

import dev.alejandro.centralservice.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor,String> {
    Optional<Profesor> findProfesorByCorreoProfesor(String correoProfesor);

}
