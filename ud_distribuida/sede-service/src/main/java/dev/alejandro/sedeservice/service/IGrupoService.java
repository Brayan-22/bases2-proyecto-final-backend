package dev.alejandro.sedeservice.service;

import dev.alejandro.sedeservice.dto.CreateGrupoRequestDto;
import dev.alejandro.sedeservice.dto.GrupoResponseDto;
import dev.alejandro.sedeservice.entity.GrupoId;
import dev.alejandro.sedeservice.exception.GrupoNotCreatedException;
import dev.alejandro.sedeservice.exception.GrupoNotFoundException;

import java.util.List;

public interface IGrupoService {
    GrupoResponseDto save(CreateGrupoRequestDto grupo) throws GrupoNotCreatedException;
    List<GrupoResponseDto> findAll();
    List<GrupoResponseDto> findByCodGrupo(String codGrupo) throws GrupoNotFoundException;
    List<GrupoResponseDto> findByPeriodoGrupo(String periodo)throws GrupoNotFoundException;
    void deleteByCodGrupo(GrupoId grupoId) throws GrupoNotFoundException;
    GrupoResponseDto update(GrupoId codGrupo, CreateGrupoRequestDto grupo) throws GrupoNotFoundException;
}
