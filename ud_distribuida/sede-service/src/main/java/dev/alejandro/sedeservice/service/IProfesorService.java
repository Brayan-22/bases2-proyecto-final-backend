package dev.alejandro.sedeservice.service;

import dev.alejandro.sedeservice.dto.CreateProfesorRequestDto;
import dev.alejandro.sedeservice.dto.ProfesorResponseDto;
import dev.alejandro.sedeservice.exception.ProfesorNotCreatedException;
import dev.alejandro.sedeservice.exception.ProfesorNotFoundException;

import java.util.List;

public interface IProfesorService {
    ProfesorResponseDto save(CreateProfesorRequestDto profesor)throws ProfesorNotCreatedException;
    List<ProfesorResponseDto> findAll()throws ProfesorNotFoundException;
    ProfesorResponseDto findById(String id) throws ProfesorNotFoundException;
    ProfesorResponseDto update(String id, CreateProfesorRequestDto profesor)throws ProfesorNotFoundException, ProfesorNotCreatedException;
    void delete(String id)throws ProfesorNotFoundException;
    ProfesorResponseDto findByCorreoInstitucional(String correoInstitucional) throws ProfesorNotFoundException;
}
