package dev.alejandro.centralservice.service.impl;

import dev.alejandro.centralservice.dto.CreateProfesorRequestDto;
import dev.alejandro.centralservice.dto.ProfesorResponseDto;
import dev.alejandro.centralservice.entity.*;
import dev.alejandro.centralservice.exception.ProfesorNotCreatedException;
import dev.alejandro.centralservice.exception.ProfesorNotFoundException;
import dev.alejandro.centralservice.repository.ClasificacionRepository;
import dev.alejandro.centralservice.repository.PregradoRepository;
import dev.alejandro.centralservice.repository.ProfesorRepository;
import dev.alejandro.centralservice.service.IProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProfesorService implements IProfesorService {

    private final ProfesorRepository profesorRepository;
    private final ClasificacionRepository clasificacionRepository;
    private final PregradoRepository pregradoRepository;
    private String generateCorreoInstitucional(String documento, String nombre, String apellido) {
        return nombre.toLowerCase().substring(0, 3) + "." + apellido.toLowerCase().substring(0, 3)
                + documento.substring(7, 9) + "@udistrital.edu.co";
    }

    @Transactional
    @Override
    public ProfesorResponseDto save(CreateProfesorRequestDto profesor) throws ProfesorNotCreatedException {
        profesorRepository.findById(profesor.getDocProfesor()).ifPresent(p -> {
            throw new ProfesorNotCreatedException("El profesor ya existe");
        });
        Pregrado pregrado = pregradoRepository.findById(profesor.getCodPregrado()).orElseThrow(()-> new ProfesorNotCreatedException("El pregrado no existe"));
        Profesor profeEntity = new Profesor();
        DetallesProfesor detallesProfesor = DetallesProfesor
                .builder()
                .profesor(profeEntity)
                .direccionProfesor(profesor.getDireccion())
                .telefonoProfesor(profesor.getTelefono())
                .correoPersonal(profesor.getCorreoPersonal())
                .build();
        Clasificacion clasificacion = clasificacionRepository.findById(profesor.getClasificacion()).orElseThrow(()-> new ProfesorNotCreatedException("La clasificacion no existe"));
        profeEntity.setPregrado(pregrado);
        profeEntity.setDocProfesor(profesor.getDocProfesor());
        profeEntity.setClasificacion(clasificacion);
        profeEntity.setNombreProfesor(profesor.getNombre());
        profeEntity.setApellidoProfesor(profesor.getApellido());
        profeEntity.setCorreoProfesor(generateCorreoInstitucional(profesor.getDocProfesor(), profesor.getNombre(), profesor.getApellido()));
        profeEntity.setDetallesProfesor(
                detallesProfesor
        );
        profesorRepository.save(profeEntity);
        return new ProfesorResponseDto(profeEntity.getDocProfesor(),
                profeEntity.getNombreProfesor(),
                profeEntity.getApellidoProfesor(),
                profeEntity.getCorreoProfesor());
    }

    @Override
    public List<ProfesorResponseDto> findAll() throws ProfesorNotFoundException {
        List<ProfesorResponseDto> profesores = profesorRepository.findAll().stream()
                .map(p -> new ProfesorResponseDto(p.getDocProfesor(), p.getNombreProfesor(), p.getApellidoProfesor(), p.getCorreoProfesor()))
                .toList();
        if (profesores.isEmpty()) throw new ProfesorNotFoundException("No hay profesores registrados");
        return profesores;
    }

    @Override
    public ProfesorResponseDto findById(String id) throws ProfesorNotFoundException {
        return profesorRepository.findById(id)
                .map(p -> new ProfesorResponseDto(p.getDocProfesor(), p.getNombreProfesor(), p.getApellidoProfesor(), p.getCorreoProfesor()))
                .orElseThrow(() -> new ProfesorNotFoundException("No se encontro el profesor"));
    }

    @Transactional
    @Override
    public ProfesorResponseDto update(String id, CreateProfesorRequestDto profesor) throws ProfesorNotFoundException, ProfesorNotCreatedException {
        Profesor profesorEntity = profesorRepository.findById(id)
                .orElseThrow(() -> new ProfesorNotFoundException("No se encontro el profesor"));
        Pregrado pregrado = pregradoRepository.findById(profesor.getCodPregrado()).orElseThrow(()-> new ProfesorNotCreatedException("El pregrado no existe"));
        profesorEntity.setNombreProfesor(profesor.getNombre());
        profesorEntity.setApellidoProfesor(profesor.getApellido());
        profesorEntity.setPregrado(pregrado);
        profesorEntity.getDetallesProfesor().setDireccionProfesor(profesor.getDireccion());
        profesorEntity.getDetallesProfesor().setTelefonoProfesor(profesor.getTelefono());
        profesorEntity.getDetallesProfesor().setCorreoPersonal(profesor.getCorreoPersonal());
        profesorRepository.save(profesorEntity);
        return new ProfesorResponseDto(profesorEntity.getDocProfesor(),
                profesorEntity.getNombreProfesor(),
                profesorEntity.getApellidoProfesor(),
                profesorEntity.getCorreoProfesor());
    }

    @Transactional
    @Override
    public void delete(String id) throws ProfesorNotFoundException {
        if (!profesorRepository.existsById(id)) throw new ProfesorNotFoundException("No se encontro el profesor");
        profesorRepository.deleteById(id);
    }

    @Override
    public ProfesorResponseDto findByCorreoInstitucional(String correoInstitucional) throws ProfesorNotFoundException {
        Profesor profesor = profesorRepository.findProfesorByCorreoProfesor(correoInstitucional)
                .orElseThrow(() -> new ProfesorNotFoundException("No se encontro el profesor"));
        return new ProfesorResponseDto(profesor.getDocProfesor(), profesor.getNombreProfesor(), profesor.getApellidoProfesor(), profesor.getCorreoProfesor());
    }

}


