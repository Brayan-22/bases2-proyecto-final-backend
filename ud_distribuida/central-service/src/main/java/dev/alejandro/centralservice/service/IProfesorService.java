package dev.alejandro.centralservice.service;

import dev.alejandro.centralservice.dto.CreateProfesorRequestDto;
import dev.alejandro.centralservice.dto.ProfesorResponseDto;
import dev.alejandro.centralservice.exception.ProfesorNotCreatedException;
import dev.alejandro.centralservice.exception.ProfesorNotFoundException;

import java.util.List;

public interface IProfesorService {
    ProfesorResponseDto save(CreateProfesorRequestDto profesor)throws ProfesorNotCreatedException;
    List<ProfesorResponseDto> findAll()throws ProfesorNotFoundException;
    ProfesorResponseDto findById(String id) throws ProfesorNotFoundException;
    ProfesorResponseDto update(String id, CreateProfesorRequestDto profesor)throws ProfesorNotFoundException, ProfesorNotCreatedException;
    void delete(String id)throws ProfesorNotFoundException;
    ProfesorResponseDto findByCorreoInstitucional(String correoInstitucional) throws ProfesorNotFoundException;
}
