package dev.alejandro.sedeservice.service;

import dev.alejandro.sedeservice.dto.AsignaturaResponseDto;
import dev.alejandro.sedeservice.dto.CreateAsignaturaRequestDto;
import dev.alejandro.sedeservice.exception.AsignaturaNotCreatedException;
import dev.alejandro.sedeservice.exception.AsignaturaNotFoundException;

import java.util.List;

public interface IAsignaturaService {
    List<AsignaturaResponseDto> findAll()throws AsignaturaNotFoundException;
    AsignaturaResponseDto findById(Long id)throws AsignaturaNotFoundException;
    AsignaturaResponseDto save(CreateAsignaturaRequestDto asignatura)throws AsignaturaNotCreatedException;
    AsignaturaResponseDto update(Long id, CreateAsignaturaRequestDto asignatura)throws AsignaturaNotCreatedException, AsignaturaNotFoundException;
    void delete(Long id)throws AsignaturaNotFoundException;
    List<AsignaturaResponseDto> findByNombreContainsIgnoreCase(String nombre)throws AsignaturaNotFoundException;
    List<AsignaturaResponseDto> findByPregrado(String nombrePregrado) throws AsignaturaNotFoundException;
}
