package dev.alejandro.sedeservice.service.impl;

import dev.alejandro.sedeservice.dto.CreatePregradoRequestDto;
import dev.alejandro.sedeservice.dto.PregradoResponseDto;
import dev.alejandro.sedeservice.dto.UpdatePregradoRequestDto;
import dev.alejandro.sedeservice.entity.Asignatura;
import dev.alejandro.sedeservice.entity.Pregrado;
import dev.alejandro.sedeservice.event.PregradoEvent;
import dev.alejandro.sedeservice.event.PregradoOperation;
import dev.alejandro.sedeservice.exception.*;
import dev.alejandro.sedeservice.repository.PregradoRepository;
import dev.alejandro.sedeservice.service.IPregradoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Sinks;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PregradoService implements IPregradoService {
    private final PregradoRepository pregradoRepository;
    private final Sinks.Many<PregradoEvent> pregradoEvent;
    @Transactional(rollbackFor = {PregradoNotCreatedException.class, AsignaturaNotCreatedException.class, EventPublicationException.class})
    @Override
    public PregradoResponseDto createPregrado(CreatePregradoRequestDto createPregradoRequestDto) throws PregradoNotCreatedException, AsignaturaNotCreatedException {
        Pregrado pregrado = new Pregrado();
        pregrado.setCodPregrado(UUID.randomUUID().toString());
        pregrado.setNombre(createPregradoRequestDto.getNombre());
        pregrado.setCreditos(createPregradoRequestDto.getCreditos());
        pregrado.setNotaMinima(createPregradoRequestDto.getNotaMinima());
        pregrado.setCorreo(createPregradoRequestDto.getCorreo());
        pregrado.setSede(createPregradoRequestDto.getSede());
        pregrado.getAsignaturas().addAll(createPregradoRequestDto.getAsignaturas().stream().map(asignaturaDto -> {
            Asignatura asignatura = new Asignatura();
            asignatura.setId(asignaturaDto.getCodAsignatura());
            asignatura.setNombreAsignatura(asignaturaDto.getNombre());
            asignatura.setHorasSemAsignatura(asignaturaDto.getHorasSemanales());
            asignatura.setEstudMaxAsignatura(asignaturaDto.getMaxEstudiantes());
            asignatura.setSemestreAsignatura(asignaturaDto.getSemestre());
            asignatura.setCodPregrado(pregrado);
            return asignatura;
        }).collect(Collectors.toSet()));
        pregradoRepository.save(pregrado);
        createPregradoRequestDto.setCodPregrado(pregrado.getCodPregrado());
        Sinks.EmitResult result = pregradoEvent.tryEmitNext(new PregradoEvent(createPregradoRequestDto, PregradoOperation.CREATE));
        if (result.isFailure()) {
            if (result == Sinks.EmitResult.FAIL_NON_SERIALIZED) {
                throw new EventPublicationException("Error al publicar el evento: El sink no está serializado");
            } else if (result == Sinks.EmitResult.FAIL_OVERFLOW) {
                throw new EventPublicationException("Error al publicar el evento: Overflow");
            } else if (result == Sinks.EmitResult.FAIL_CANCELLED) {
                throw new EventPublicationException("Error al publicar el evento: Cancelado");
            } else {
                throw new EventPublicationException("Error al publicar el evento: Desconocido");
            }
        }
        return new PregradoResponseDto(pregrado.getCodPregrado(), pregrado.getNombre(), pregrado.getCreditos(), pregrado.getNotaMinima(), pregrado.getCorreo());
    }

    @Override
    public List<PregradoResponseDto> getAllPregrados() throws PregradoNotFoundException {
        List<PregradoResponseDto> pregrados = pregradoRepository.findAll()
                .stream()
                .map(pregrado -> new PregradoResponseDto(pregrado.getCodPregrado(), pregrado.getNombre(), pregrado.getCreditos(), pregrado.getNotaMinima(), pregrado.getCorreo())).toList();
        if (pregrados.isEmpty()) throw new PregradoNotFoundException("No pregrados found");
        return pregrados;
    }

    @Override
    public PregradoResponseDto getPregradoById(String codPregrado) throws PregradoNotFoundException {
        Pregrado pregrado = pregradoRepository.findById(codPregrado).orElseThrow(()-> new PregradoNotFoundException("Pregrado not found"));
        return new PregradoResponseDto(pregrado.getCodPregrado(), pregrado.getNombre(), pregrado.getCreditos(), pregrado.getNotaMinima(), pregrado.getCorreo());
    }

    @Override
    public PregradoResponseDto getPregradoByName(String nombrePregrado) throws PregradoNotFoundException {
        Pregrado pregrado = pregradoRepository.findByNombreContainsIgnoreCase(nombrePregrado).orElseThrow(()-> new PregradoNotFoundException("Pregrado not found"));
        return new PregradoResponseDto(pregrado.getCodPregrado(), pregrado.getNombre(), pregrado.getCreditos(), pregrado.getNotaMinima(), pregrado.getCorreo());
    }

    @Transactional(rollbackFor = {PregradoNotCreatedException.class, EventPublicationException.class})
    @Override
    public PregradoResponseDto updatePregrado(String codPregrado, UpdatePregradoRequestDto createPregradoRequestDto) throws PregradoNotCreatedException {
        Pregrado pregrado = pregradoRepository.findById(codPregrado).orElseThrow(()-> new PregradoNotCreatedException("Pregrado not found"));
        pregrado.setNombre(createPregradoRequestDto.getNombre());
        pregrado.setCreditos(createPregradoRequestDto.getCreditos());
        pregrado.setNotaMinima(createPregradoRequestDto.getNotaMinima());
        pregrado.setCorreo(createPregradoRequestDto.getCorreo());
        pregradoRepository.save(pregrado);
        Sinks.EmitResult result = pregradoEvent.tryEmitNext(new PregradoEvent(new CreatePregradoRequestDto(
                createPregradoRequestDto.getCodPregrado(),createPregradoRequestDto.getNombre(),createPregradoRequestDto.getCreditos(),createPregradoRequestDto.getNotaMinima(),createPregradoRequestDto.getCorreo(),createPregradoRequestDto.getSede(),null
        ), PregradoOperation.UPDATE));
        if (result.isFailure()) {
            if (result == Sinks.EmitResult.FAIL_NON_SERIALIZED) {
                throw new EventPublicationException("Error al publicar el evento: El sink no está serializado");
            } else if (result == Sinks.EmitResult.FAIL_OVERFLOW) {
                throw new EventPublicationException("Error al publicar el evento: Overflow");
            } else if (result == Sinks.EmitResult.FAIL_CANCELLED) {
                throw new EventPublicationException("Error al publicar el evento: Cancelado");
            } else {
                throw new EventPublicationException("Error al publicar el evento: Desconocido");
            }
        }
        return new PregradoResponseDto(pregrado.getCodPregrado(), pregrado.getNombre(), pregrado.getCreditos(), pregrado.getNotaMinima(), pregrado.getCorreo());
    }

    @Transactional(rollbackFor = {PregradoNotFoundException.class, EventPublicationException.class})
    @Override
    public void deletePregrado(String codPregrado) throws PregradoNotFoundException {
        Pregrado pregrado = pregradoRepository.findById(codPregrado).orElseThrow(()-> new PregradoNotFoundException("Pregrado not found"));
        pregradoRepository.delete(pregrado);
        CreatePregradoRequestDto createPregradoRequestDto = new CreatePregradoRequestDto();
        createPregradoRequestDto.setCodPregrado(codPregrado);
        Sinks.EmitResult result = pregradoEvent.tryEmitNext(new PregradoEvent(createPregradoRequestDto, PregradoOperation.DELETE));
        if (result.isFailure()) {
            if (result == Sinks.EmitResult.FAIL_NON_SERIALIZED) {
                throw new EventPublicationException("Error al publicar el evento: El sink no está serializado");
            } else if (result == Sinks.EmitResult.FAIL_OVERFLOW) {
                throw new EventPublicationException("Error al publicar el evento: Overflow");
            } else if (result == Sinks.EmitResult.FAIL_CANCELLED) {
                throw new EventPublicationException("Error al publicar el evento: Cancelado");
            } else {
                throw new EventPublicationException("Error al publicar el evento: Desconocido");
            }
        }
    }
}
