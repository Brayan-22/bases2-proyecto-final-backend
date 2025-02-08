package dev.alejandro.centralservice.service.impl;

import dev.alejandro.centralservice.dto.DictarRequestDto;
import dev.alejandro.centralservice.dto.DictarResponseDto;
import dev.alejandro.centralservice.entity.*;
import dev.alejandro.centralservice.exception.DictarNotCreatedException;
import dev.alejandro.centralservice.exception.DictarNotFoundException;
import dev.alejandro.centralservice.repository.AsignaturaRepository;
import dev.alejandro.centralservice.repository.DictarRepository;
import dev.alejandro.centralservice.repository.GrupoRepository;
import dev.alejandro.centralservice.repository.ProfesorRepository;
import dev.alejandro.centralservice.service.IDictarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DictarService implements IDictarService {

    private final DictarRepository dictarRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final ProfesorRepository profesorRepository;
    private final GrupoRepository grupoRepository;
    @Override
    public DictarResponseDto save(DictarRequestDto requestDto) throws DictarNotCreatedException {
        Dictar dictar = new Dictar();

        Grupo grupo = grupoRepository.findById(new GrupoId(requestDto.getCodGrupo(), requestDto.getPeriodoGrupo())).orElseThrow(() ->
                new DictarNotCreatedException("Grupo no encontrado"));
        Profesor profesor = profesorRepository.findById(requestDto.getDocProfesor()).orElseThrow(() ->
                new DictarNotCreatedException("Profesor no encontrado"));

        dictar.setGrupo(grupo);
        dictar.setId(new DictarId(profesor.getDocProfesor(), grupo.getId()));
        dictar.setDocProfesor(profesor);
        dictar.setHoraSemDictar(requestDto.getHorasSemana());
        dictarRepository.save(dictar);
        return new DictarResponseDto(grupo.getId().getCodGrupo(),
                grupo.getId().getPeriodoGrupo(), grupo.getAsignatura().getNombreAsignatura(), profesor.getCorreoProfesor());
    }

    @Override
    public List<DictarResponseDto> findAll() throws DictarNotFoundException {
        List<DictarResponseDto> dictarList = dictarRepository.findAll().stream()
                .map(d -> new DictarResponseDto(
                        d.getGrupo().getId().getCodGrupo(),
                        d.getGrupo().getId().getPeriodoGrupo(),
                        d.getGrupo().getAsignatura().getNombreAsignatura(),
                        d.getDocProfesor().getCorreoProfesor()
                )).toList();
        if (dictarList.isEmpty()) throw new DictarNotFoundException("No se encontraron dictados");
        return dictarList;
    }

    @Override
    public DictarResponseDto update(DictarId dictarId, DictarRequestDto requestDto) throws DictarNotCreatedException, DictarNotFoundException {
        Dictar dictar = dictarRepository.findById(dictarId).orElseThrow(() ->
                new DictarNotFoundException("Dictado no encontrado"));
        Grupo grupo = grupoRepository.findById(new GrupoId(requestDto.getCodGrupo(), requestDto.getPeriodoGrupo())).orElseThrow(() ->
                new DictarNotCreatedException("Grupo no encontrado"));
        Profesor profesor = profesorRepository.findById(requestDto.getDocProfesor()).orElseThrow(() ->
                new DictarNotCreatedException("Profesor no encontrado"));

        dictar.setGrupo(grupo);
        dictar.setId(new DictarId(profesor.getDocProfesor(), grupo.getId()));
        dictar.setDocProfesor(profesor);
        dictar.setHoraSemDictar(requestDto.getHorasSemana());
        dictarRepository.save(dictar);
        return new DictarResponseDto(grupo.getId().getCodGrupo(),
                grupo.getId().getPeriodoGrupo(), grupo.getAsignatura().getNombreAsignatura(), profesor.getCorreoProfesor());
    }

    @Override
    public void deleteByCodDictar(DictarId dictarId) throws DictarNotFoundException {
        Dictar dictar = dictarRepository.findById(dictarId).orElseThrow(() ->
                new DictarNotFoundException("Dictado no encontrado"));
        dictarRepository.delete(dictar);
    }
}
