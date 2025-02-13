package dev.alejandro.centralservice.service;


import dev.alejandro.centralservice.dto.CreatePregradoRequestDto;
import dev.alejandro.centralservice.dto.PregradoResponseDto;
import dev.alejandro.centralservice.dto.UpdatePregradoRequestDto;
import dev.alejandro.centralservice.exception.AsignaturaNotCreatedException;
import dev.alejandro.centralservice.exception.PregradoNotCreatedException;
import dev.alejandro.centralservice.exception.PregradoNotFoundException;

import java.util.List;

public interface IPregradoService {
    PregradoResponseDto createPregrado(CreatePregradoRequestDto createPregradoRequestDto) throws PregradoNotCreatedException, AsignaturaNotCreatedException;
    List<PregradoResponseDto> getAllPregrados()throws PregradoNotFoundException;
    PregradoResponseDto getPregradoById(String codPregrado) throws PregradoNotFoundException;
    PregradoResponseDto getPregradoByName(String nombrePregrado) throws PregradoNotFoundException;
    PregradoResponseDto updatePregrado(String codPregrado, UpdatePregradoRequestDto createPregradoRequestDto) throws PregradoNotCreatedException;
    void deletePregrado(String codPregrado)throws PregradoNotFoundException;
}
