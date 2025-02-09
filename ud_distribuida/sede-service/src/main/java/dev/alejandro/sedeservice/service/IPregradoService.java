package dev.alejandro.sedeservice.service;

import dev.alejandro.sedeservice.dto.CreatePregradoRequestDto;
import dev.alejandro.sedeservice.dto.PregradoResponseDto;
import dev.alejandro.sedeservice.dto.UpdatePregradoRequestDto;
import dev.alejandro.sedeservice.exception.AsignaturaNotCreatedException;
import dev.alejandro.sedeservice.exception.PregradoNotCreatedException;
import dev.alejandro.sedeservice.exception.PregradoNotFoundException;

import java.util.List;

public interface IPregradoService {
    PregradoResponseDto createPregrado(CreatePregradoRequestDto createPregradoRequestDto) throws PregradoNotCreatedException, AsignaturaNotCreatedException;
    List<PregradoResponseDto> getAllPregrados()throws PregradoNotFoundException;
    PregradoResponseDto getPregradoById(Integer codPregrado) throws PregradoNotFoundException;
    PregradoResponseDto getPregradoByName(String nombrePregrado) throws PregradoNotFoundException;
    PregradoResponseDto updatePregrado(Integer codPregrado, UpdatePregradoRequestDto createPregradoRequestDto) throws PregradoNotCreatedException;
    void deletePregrado(Integer codPregrado)throws PregradoNotFoundException;
}
