package dev.alejandro.centralservice.service;

import dev.alejandro.centralservice.dto.CreateGrupoRequestDto;
import dev.alejandro.centralservice.dto.GrupoResponseDto;
import dev.alejandro.centralservice.entity.GrupoId;
import dev.alejandro.centralservice.exception.GrupoNotCreatedException;
import dev.alejandro.centralservice.exception.GrupoNotFoundException;

import java.util.List;

public interface IGrupoService {
    GrupoResponseDto save(CreateGrupoRequestDto grupo) throws GrupoNotCreatedException;
    List<GrupoResponseDto> findAll();
    List<GrupoResponseDto> findByCodGrupo(String codGrupo) throws GrupoNotFoundException;
    List<GrupoResponseDto> findByPeriodoGrupo(String periodo)throws GrupoNotFoundException;
    void deleteByCodGrupo(GrupoId grupoId) throws GrupoNotFoundException;
    GrupoResponseDto update(GrupoId codGrupo, CreateGrupoRequestDto grupo) throws GrupoNotFoundException;
}
