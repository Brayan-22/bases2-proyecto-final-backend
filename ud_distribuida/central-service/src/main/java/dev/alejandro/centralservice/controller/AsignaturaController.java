package dev.alejandro.centralservice.controller;

import dev.alejandro.centralservice.dto.AsignaturaResponseDto;
import dev.alejandro.centralservice.dto.CreateAsignaturaRequestDto;
import dev.alejandro.centralservice.service.IAsignaturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/asignatura")
@RequiredArgsConstructor
public class AsignaturaController {
    private final IAsignaturaService asignaturaService;

    @GetMapping("/all")
    public Mono<ResponseEntity<List<AsignaturaResponseDto>>> findAll() {
        return Mono.just(ResponseEntity.ok(asignaturaService.findAll()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<AsignaturaResponseDto>> findById(@PathVariable Long id) {
        return Mono.just(ResponseEntity.ok(asignaturaService.findById(id)));
    }
    @GetMapping
    public Mono<ResponseEntity<List<AsignaturaResponseDto>>> findByNombre(@RequestParam String nombre) {
        return Mono.just(ResponseEntity.ok(asignaturaService.findByNombreContainsIgnoreCase(nombre)));
    }

    @GetMapping("/pregrado")
    public Mono<ResponseEntity<List<AsignaturaResponseDto>>> findByPregrado(@RequestParam String nombre) {
        return Mono.just(ResponseEntity.ok(asignaturaService.findByPregrado(nombre)));
    }

    @PostMapping
    public Mono<ResponseEntity<AsignaturaResponseDto>> save(@Valid@RequestBody CreateAsignaturaRequestDto asignatura) {
        return Mono.just(ResponseEntity.ok(asignaturaService.save(asignatura)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<AsignaturaResponseDto>> update(@PathVariable Long id, @Valid@RequestBody CreateAsignaturaRequestDto asignatura) {
        return Mono.just(ResponseEntity.ok(asignaturaService.update(id, asignatura)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
        asignaturaService.delete(id);
        return Mono.just(ResponseEntity.noContent().build());
    }
}
