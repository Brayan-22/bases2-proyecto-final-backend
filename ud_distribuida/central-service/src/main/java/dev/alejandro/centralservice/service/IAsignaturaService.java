package dev.alejandro.centralservice.service;

import dev.alejandro.centralservice.dto.AsignaturaResponseDto;
import dev.alejandro.centralservice.dto.CreateAsignaturaDto;
import dev.alejandro.centralservice.dto.CreateAsignaturaRequestDto;
import dev.alejandro.centralservice.exception.AsignaturaNotCreatedException;
import dev.alejandro.centralservice.exception.AsignaturaNotFoundException;

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
