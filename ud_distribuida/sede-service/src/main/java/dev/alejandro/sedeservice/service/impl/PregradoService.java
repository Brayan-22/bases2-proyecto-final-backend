package dev.alejandro.sedeservice.service.impl;

import dev.alejandro.sedeservice.dto.CreatePregradoRequestDto;
import dev.alejandro.sedeservice.dto.PregradoResponseDto;
import dev.alejandro.sedeservice.dto.UpdatePregradoRequestDto;
import dev.alejandro.sedeservice.entity.Asignatura;
import dev.alejandro.sedeservice.entity.Pregrado;
import dev.alejandro.sedeservice.exception.AsignaturaNotCreatedException;
import dev.alejandro.sedeservice.exception.PregradoNotCreatedException;
import dev.alejandro.sedeservice.exception.PregradoNotFoundException;
import dev.alejandro.sedeservice.repository.PregradoRepository;
import dev.alejandro.sedeservice.service.IPregradoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PregradoService implements IPregradoService {
    private final PregradoRepository pregradoRepository;

    @Transactional
    @Override
    public PregradoResponseDto createPregrado(CreatePregradoRequestDto createPregradoRequestDto) throws PregradoNotCreatedException, AsignaturaNotCreatedException {
        pregradoRepository.findById(createPregradoRequestDto.getCodPregrado()).ifPresent(pregradoEntity -> {
            throw new PregradoNotCreatedException("Pregrado already exists");
        });
        Pregrado pregrado = new Pregrado();
        pregrado.setCodPregrado(createPregradoRequestDto.getCodPregrado());
        pregrado.setNombre(createPregradoRequestDto.getNombre());
        pregrado.setCreditosPregrado(createPregradoRequestDto.getCreditos());
        pregrado.setNotaMinima(createPregradoRequestDto.getNotaMinima());
        pregrado.setCorreoPregrado(createPregradoRequestDto.getCorreo());
        pregrado.setSede(createPregradoRequestDto.getSede());
        pregrado.getAsignaturas().addAll(createPregradoRequestDto.getAsignaturas().stream().map(asignaturaDto -> {
            Asignatura asignatura = new Asignatura();
            asignatura.setId(asignaturaDto.getCodAsignatura());
            asignatura.setNombreAsignatura(asignaturaDto.getNombre());
            asignatura.setHorasSemAsignatura(asignaturaDto.getHorasSemanales());
            asignatura.setEstudMaxAsignatura(asignaturaDto.getMaxEstudiantes());
            asignatura.setSemestreAsignatura(asignaturaDto.getSemestre());
            asignatura.setPregrado(pregrado);
            return asignatura;
        }).collect(Collectors.toSet()));
        pregradoRepository.saveAndFlush(pregrado);
        return new PregradoResponseDto(pregrado.getCodPregrado(), pregrado.getNombre(), pregrado.getCreditosPregrado(), pregrado.getNotaMinima(), pregrado.getCorreoPregrado());
    }

    @Override
    public List<PregradoResponseDto> getAllPregrados() throws PregradoNotFoundException {
        List<PregradoResponseDto> pregrados = pregradoRepository.findAll()
                .stream()
                .map(pregrado -> new PregradoResponseDto(pregrado.getCodPregrado(), pregrado.getNombre(), pregrado.getCreditosPregrado(), pregrado.getNotaMinima(), pregrado.getCorreoPregrado())).toList();
        if (pregrados.isEmpty()) throw new PregradoNotFoundException("No pregrados found");
        return pregrados;
    }

    @Override
    public PregradoResponseDto getPregradoById(Integer codPregrado) throws PregradoNotFoundException {
        Pregrado pregrado = pregradoRepository.findById(codPregrado).orElseThrow(()-> new PregradoNotFoundException("Pregrado not found"));
        return new PregradoResponseDto(pregrado.getCodPregrado(), pregrado.getNombre(), pregrado.getCreditosPregrado(), pregrado.getNotaMinima(), pregrado.getCorreoPregrado());
    }

    @Override
    public PregradoResponseDto getPregradoByName(String nombrePregrado) throws PregradoNotFoundException {
        Pregrado pregrado = pregradoRepository.findByNombreContainsIgnoreCase(nombrePregrado).orElseThrow(()-> new PregradoNotFoundException("Pregrado not found"));
        return new PregradoResponseDto(pregrado.getCodPregrado(), pregrado.getNombre(), pregrado.getCreditosPregrado(), pregrado.getNotaMinima(), pregrado.getCorreoPregrado());
    }

    @Transactional
    @Override
    public PregradoResponseDto updatePregrado(Integer codPregrado, UpdatePregradoRequestDto createPregradoRequestDto) throws PregradoNotCreatedException {
        Pregrado pregrado = pregradoRepository.findById(codPregrado).orElseThrow(()-> new PregradoNotCreatedException("Pregrado not found"));
        pregrado.setNombre(createPregradoRequestDto.getNombre());
        pregrado.setCreditosPregrado(createPregradoRequestDto.getCreditos());
        pregrado.setNotaMinima(createPregradoRequestDto.getNotaMinima());
        pregrado.setCorreoPregrado(createPregradoRequestDto.getCorreo());
        pregradoRepository.saveAndFlush(pregrado);
        return new PregradoResponseDto(pregrado.getCodPregrado(), pregrado.getNombre(), pregrado.getCreditosPregrado(), pregrado.getNotaMinima(), pregrado.getCorreoPregrado());
    }

    @Transactional
    @Override
    public void deletePregrado(Integer codPregrado) throws PregradoNotFoundException {
        Pregrado pregrado = pregradoRepository.findById(codPregrado).orElseThrow(()-> new PregradoNotFoundException("Pregrado not found"));
        pregradoRepository.delete(pregrado);
    }
}
