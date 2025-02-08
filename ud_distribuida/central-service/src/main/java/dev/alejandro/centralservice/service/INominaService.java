package dev.alejandro.centralservice.service;

import dev.alejandro.centralservice.dto.CreatePagoNominaRequestDto;
import dev.alejandro.centralservice.dto.PagoNominaResponseDto;
import dev.alejandro.centralservice.exception.NominaNotCreatedException;
import dev.alejandro.centralservice.exception.NominaNotFoundException;

import java.util.List;

public interface INominaService {
    PagoNominaResponseDto pagarNomina(CreatePagoNominaRequestDto request) throws NominaNotCreatedException;
    List<PagoNominaResponseDto> listarPagos()throws NominaNotFoundException;
}
