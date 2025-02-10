package dev.alejandro.sedeservice.service.impl;


import dev.alejandro.sedeservice.dto.AsignaturaResponseDto;
import dev.alejandro.sedeservice.dto.CreateAsignaturaRequestDto;
import dev.alejandro.sedeservice.entity.Asignatura;
import dev.alejandro.sedeservice.entity.Pregrado;
import dev.alejandro.sedeservice.exception.AsignaturaNotCreatedException;
import dev.alejandro.sedeservice.exception.AsignaturaNotFoundException;
import dev.alejandro.sedeservice.repository.AsignaturaRepository;
import dev.alejandro.sedeservice.repository.PregradoRepository;
import dev.alejandro.sedeservice.service.IAsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsignaturaService implements IAsignaturaService {

    private final AsignaturaRepository asignaturaRepository;
    private final PregradoRepository pregradoRepository;

    @Override
    public List<AsignaturaResponseDto> findAll() throws AsignaturaNotFoundException {
        List<AsignaturaResponseDto> asignaturas = asignaturaRepository.findAll()
                .stream().map(ae -> {
                    return new AsignaturaResponseDto(
                            ae.getId(),
                            ae.getNombreAsignatura(),
                            ae.getHorasSemAsignatura(),
                            ae.getEstudMaxAsignatura(),
                            ae.getSemestreAsignatura(),
                            ae.getCodPregrado().getNombre()
                    );
                }).toList();
        if (asignaturas.isEmpty()) throw new AsignaturaNotFoundException("No se encontraron asignaturas");
        return asignaturas;
    }

    @Override
    public AsignaturaResponseDto findById(Long id) throws AsignaturaNotFoundException {
        return asignaturaRepository.findById(id)
                .map(ae -> new AsignaturaResponseDto(
                        ae.getId(),
                        ae.getNombreAsignatura(),
                        ae.getHorasSemAsignatura(),
                        ae.getEstudMaxAsignatura(),
                        ae.getSemestreAsignatura(),
                        ae.getCodPregrado().getNombre()
                )).orElseThrow(() -> new AsignaturaNotFoundException("Asignatura no encontrada"));
    }

    @Transactional
    @Override
    public AsignaturaResponseDto save(CreateAsignaturaRequestDto asignatura) throws AsignaturaNotCreatedException {
        asignaturaRepository.findById(asignatura.getCodAsignatura()).ifPresent(ae -> {
            throw new AsignaturaNotCreatedException("Asignatura ya existe");
        });
        Asignatura asignaturaEntity = new Asignatura();
        asignaturaEntity.setId(asignatura.getCodAsignatura());
        asignaturaEntity.setNombreAsignatura(asignatura.getNombre());
        asignaturaEntity.setHorasSemAsignatura(asignatura.getHorasSemanales());
        asignaturaEntity.setEstudMaxAsignatura(asignatura.getMaxEstudiantes());
        asignaturaEntity.setSemestreAsignatura(asignatura.getSemestre());
        asignaturaEntity.setCodPregrado(pregradoRepository.findByNombreContainsIgnoreCase(asignatura.getPregrado())
                .orElseThrow(() -> new AsignaturaNotCreatedException("Pregrado no encontrado")));
        asignaturaRepository.save(asignaturaEntity);
        return new AsignaturaResponseDto(
                asignaturaEntity.getId(),
                asignaturaEntity.getNombreAsignatura(),
                asignaturaEntity.getHorasSemAsignatura(),
                asignaturaEntity.getEstudMaxAsignatura(),
                asignaturaEntity.getSemestreAsignatura(),
                asignaturaEntity.getCodPregrado().getNombre()
        );
    }

    @Transactional
    @Override
    public AsignaturaResponseDto update(Long id, CreateAsignaturaRequestDto asignatura) throws AsignaturaNotCreatedException, AsignaturaNotFoundException {
        Asignatura asignaturaEntity = asignaturaRepository.findById(id)
                .orElseThrow(() -> new AsignaturaNotFoundException("Asignatura no encontrada"));
        asignaturaEntity.setNombreAsignatura(asignatura.getNombre());
        asignaturaEntity.setHorasSemAsignatura(asignatura.getHorasSemanales());
        asignaturaEntity.setEstudMaxAsignatura(asignatura.getMaxEstudiantes());
        asignaturaEntity.setSemestreAsignatura(asignatura.getSemestre());
        asignaturaEntity.setCodPregrado(pregradoRepository.findByNombreContainsIgnoreCase(asignatura.getPregrado())
                .orElseThrow(() -> new AsignaturaNotCreatedException("Pregrado no encontrado")));
        asignaturaRepository.saveAndFlush(asignaturaEntity);
        return new AsignaturaResponseDto(
                asignaturaEntity.getId(),
                asignaturaEntity.getNombreAsignatura(),
                asignaturaEntity.getHorasSemAsignatura(),
                asignaturaEntity.getEstudMaxAsignatura(),
                asignaturaEntity.getSemestreAsignatura(),
                asignaturaEntity.getCodPregrado().getNombre()
        );
    }

    @Transactional
    @Override
    public void delete(Long id) throws AsignaturaNotFoundException {
        if (!asignaturaRepository.existsById(id)) throw new AsignaturaNotFoundException("Asignatura no encontrada");
        asignaturaRepository.deleteById(id);
    }

    @Override
    public List<AsignaturaResponseDto> findByNombreContainsIgnoreCase(String nombre) throws AsignaturaNotFoundException {
        List<AsignaturaResponseDto> asignaturas = asignaturaRepository.findByNombreAsignaturaContainsIgnoreCase(nombre)
                .stream().map(ae -> new AsignaturaResponseDto(
                        ae.getId(),
                        ae.getNombreAsignatura(),
                        ae.getHorasSemAsignatura(),
                        ae.getEstudMaxAsignatura(),
                        ae.getSemestreAsignatura(),
                        ae.getCodPregrado().getNombre()
                )).toList();
        if (asignaturas.isEmpty()) throw new AsignaturaNotFoundException("No se encontraron asignaturas");
        return asignaturas;
    }

    @Transactional
    @Override
    public List<AsignaturaResponseDto> findByPregrado(String nombrePregrado) throws AsignaturaNotFoundException {
        Pregrado pregrado = pregradoRepository.findByNombreContainsIgnoreCase(nombrePregrado)
                .orElseThrow(() -> new AsignaturaNotFoundException("Pregrado no encontrado"));
        List<AsignaturaResponseDto> asignaturas = pregrado.getAsignaturas().stream()
                .toList().stream().map(ae -> new AsignaturaResponseDto(
                        ae.getId(),
                        ae.getNombreAsignatura(),
                        ae.getHorasSemAsignatura(),
                        ae.getEstudMaxAsignatura(),
                        ae.getSemestreAsignatura(),
                        ae.getCodPregrado().getNombre()
                )).toList();
        if (asignaturas.isEmpty()) throw new AsignaturaNotFoundException("No se encontraron asignaturas");
        return asignaturas;
    }
}
