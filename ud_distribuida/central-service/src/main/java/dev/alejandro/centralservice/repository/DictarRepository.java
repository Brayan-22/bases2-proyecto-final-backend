package dev.alejandro.centralservice.repository;

import dev.alejandro.centralservice.entity.Dictar;
import dev.alejandro.centralservice.entity.DictarId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictarRepository extends JpaRepository<Dictar, DictarId> {
}