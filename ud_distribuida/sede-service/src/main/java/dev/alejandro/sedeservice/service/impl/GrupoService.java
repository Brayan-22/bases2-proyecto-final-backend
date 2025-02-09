package dev.alejandro.sedeservice.service.impl;

import dev.alejandro.sedeservice.dto.CreateGrupoRequestDto;
import dev.alejandro.sedeservice.dto.GrupoResponseDto;
import dev.alejandro.sedeservice.entity.Asignatura;
import dev.alejandro.sedeservice.entity.Grupo;
import dev.alejandro.sedeservice.entity.GrupoId;
import dev.alejandro.sedeservice.exception.GrupoNotCreatedException;
import dev.alejandro.sedeservice.exception.GrupoNotFoundException;
import dev.alejandro.sedeservice.repository.AsignaturaRepository;
import dev.alejandro.sedeservice.repository.GrupoRepository;
import dev.alejandro.sedeservice.service.IGrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GrupoService implements IGrupoService {

    private final GrupoRepository grupoRepository;
    private final AsignaturaRepository asignaturaRepository;
    @Override
    public GrupoResponseDto save(CreateGrupoRequestDto requestDto) throws GrupoNotCreatedException {
        Asignatura asignatura = asignaturaRepository.findById(requestDto.getCodAsignatura()).orElseThrow(() -> new GrupoNotCreatedException("La asignatura no existe"));
        Grupo grupo = Grupo.builder()
                .id(new GrupoId(requestDto.getCodGrupo(), requestDto.getPeriodoGrupo()))
                .codAsignatura(asignatura)
                .build();
        grupoRepository.save(grupo);
        return new GrupoResponseDto(grupo.getId().getCodGrupo(), grupo.getId().getPeriodoGrupo(),asignatura.getNombreAsignatura());
    }

    @Override
    public List<GrupoResponseDto> findAll() {
        List<GrupoResponseDto> grupos = grupoRepository.findAll().stream()
                .map(g -> new GrupoResponseDto(g.getId().getCodGrupo(), g.getId().getPeriodoGrupo(), g.getCodAsignatura().getNombreAsignatura()))
                .toList();
        if (grupos.isEmpty())throw new GrupoNotFoundException("No hay grupos registrados");
        return grupos;
    }

    @Override
    public List<GrupoResponseDto> findByCodGrupo(String codGrupo) throws GrupoNotFoundException {
        List<Grupo> grupo = grupoRepository.findById_CodGrupo(codGrupo);
        if (grupo.isEmpty()) throw new GrupoNotFoundException("Grupos no encontrados");
        return grupo.stream()
                .map(g -> new GrupoResponseDto(g.getId().getCodGrupo(),g.getId().getPeriodoGrupo(), g.getCodAsignatura().getNombreAsignatura()))
                .toList();
    }

    @Override
    public List<GrupoResponseDto> findByPeriodoGrupo(String periodo) throws GrupoNotFoundException {
        List<Grupo> grupo = grupoRepository.findById_PeriodoGrupo(periodo);
        if (grupo.isEmpty()) throw new GrupoNotFoundException("Grupos no encontrados");
        return grupo.stream()
                .map(g -> new GrupoResponseDto(g.getId().getCodGrupo(),g.getId().getPeriodoGrupo(), g.getCodAsignatura().getNombreAsignatura()))
                .toList();
    }

    @Override
    public void deleteByCodGrupo(GrupoId grupoId) throws GrupoNotFoundException {
        grupoRepository.findById(grupoId).orElseThrow(()-> new GrupoNotFoundException("Grupo no encontrado"));
        grupoRepository.deleteById(grupoId);
    }


    @Override
    public GrupoResponseDto update(GrupoId grupoId, CreateGrupoRequestDto grupo) throws GrupoNotFoundException {
        Grupo grupoEntity = grupoRepository.findById(grupoId).orElseThrow(()-> new GrupoNotFoundException("Grupo no encontrado"));
        Asignatura asignatura = asignaturaRepository.findById(grupo.getCodAsignatura()).orElseThrow(()-> new GrupoNotFoundException("Asignatura no encontrada"));
        grupoEntity.setId(new GrupoId(grupo.getCodGrupo(),grupo.getPeriodoGrupo()));
        grupoEntity.setCodAsignatura(asignatura);
        grupoRepository.save(grupoEntity);
        return new GrupoResponseDto(grupoEntity.getId().getCodGrupo(),grupoEntity.getId().getPeriodoGrupo(),asignatura.getNombreAsignatura());
    }
}
