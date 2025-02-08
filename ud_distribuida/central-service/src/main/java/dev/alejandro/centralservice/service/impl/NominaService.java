package dev.alejandro.centralservice.service.impl;

import dev.alejandro.centralservice.dto.CreatePagoNominaRequestDto;
import dev.alejandro.centralservice.dto.PagoNominaResponseDto;
import dev.alejandro.centralservice.dto.ProfesorResponseDto;
import dev.alejandro.centralservice.entity.*;
import dev.alejandro.centralservice.exception.NominaNotCreatedException;
import dev.alejandro.centralservice.exception.NominaNotFoundException;
import dev.alejandro.centralservice.repository.NominaProfesorRepository;
import dev.alejandro.centralservice.repository.ProfesorRepository;
import dev.alejandro.centralservice.service.INominaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NominaService implements INominaService {

    private final NominaProfesorRepository nominaProfesorRepository;
    private final Random random = new Random();
    private final ProfesorRepository profesorRepository;
    @Transactional
    @Override
    public PagoNominaResponseDto pagarNomina(CreatePagoNominaRequestDto request) throws NominaNotCreatedException {
        NominaProfesor nominaProfesor = new NominaProfesor();
        Profesor profesor = profesorRepository.findById(request.getDocProfesor()).orElseThrow(()-> new NominaNotCreatedException("Profesor no encontrado"));
        Nomina nomina = new Nomina();
        nomina.setIdNomina(UUID.randomUUID().toString());
        nomina.setFechaFin(request.getFechaFin());
        nomina.setFechaInicio(request.getFechaInicio());
        nomina.setEstado(getEstadoPago());
        nominaProfesor.setNomina(nomina);
        nominaProfesor.setProfesor(profesor);
        nominaProfesor.setId(new NominaProfesorId(request.getDocProfesor(),nomina.getIdNomina()));
        nominaProfesor.setPago(BigDecimal.valueOf(request.getPago()));
        nominaProfesorRepository.save(nominaProfesor);
        return new PagoNominaResponseDto(nomina.getIdNomina(),nomina.getEstado().toString());
    }

    private EstadoPagoEnum getEstadoPago() {
        int index = random.nextInt(EstadoPagoEnum.values().length);
        return EstadoPagoEnum.values()[index];
    }

    @Transactional
    @Override
    public List<PagoNominaResponseDto> listarPagos() throws NominaNotFoundException {
        List<PagoNominaResponseDto> pagos = nominaProfesorRepository.findAll().stream()
                .map(p -> new PagoNominaResponseDto(p.getNomina().getIdNomina(), p.getNomina().getEstado().toString()))
                .toList();
        if(pagos.isEmpty()) throw new NominaNotFoundException("No se encontraron pagos");
        return pagos;
    }
}
