package dev.alejandro.centralservice.repository;

import dev.alejandro.centralservice.entity.Grupo;
import dev.alejandro.centralservice.entity.GrupoId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, GrupoId> {
    List<Grupo> findById_CodGrupo(@Size(max = 10) @NotNull String idCodGrupo);

    List<Grupo> findById_PeriodoGrupo(@Size(max = 10) @NotNull String idPeriodoGrupo);
}