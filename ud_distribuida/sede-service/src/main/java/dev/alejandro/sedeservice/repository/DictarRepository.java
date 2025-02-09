package dev.alejandro.sedeservice.repository;

import dev.alejandro.sedeservice.entity.Dictar;
import dev.alejandro.sedeservice.entity.DictarId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictarRepository extends JpaRepository<Dictar, DictarId> {
}