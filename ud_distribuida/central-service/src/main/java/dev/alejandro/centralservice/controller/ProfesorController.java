package dev.alejandro.centralservice.controller;

import dev.alejandro.centralservice.dto.CreateProfesorRequestDto;
import dev.alejandro.centralservice.dto.ProfesorResponseDto;
import dev.alejandro.centralservice.service.IProfesorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/profesor")
@RequiredArgsConstructor
public class ProfesorController {
    private final IProfesorService profesorService;

    @PostMapping
    public Mono<ResponseEntity<ProfesorResponseDto>> save(@Valid@RequestBody CreateProfesorRequestDto profesor){
        return Mono.just(ResponseEntity.ok(profesorService.save(profesor)));
    }

    @GetMapping("/all")
    public Mono<List<ProfesorResponseDto>> findAll(){
        return Mono.just(profesorService.findAll());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProfesorResponseDto>> findById(@PathVariable String id){
        return Mono.just(ResponseEntity.ok(profesorService.findById(id)));
    }

    @GetMapping
    public Mono<ResponseEntity<ProfesorResponseDto>> findByCorreoInstitucional(@RequestParam String correo){
        return Mono.just(ResponseEntity.ok(profesorService.findByCorreoInstitucional(correo)));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProfesorResponseDto>> update(@PathVariable String id, @Valid@RequestBody CreateProfesorRequestDto profesor){
        return Mono.just(ResponseEntity.ok(profesorService.update(id, profesor)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<ProfesorResponseDto>> delete(@PathVariable String id){
        profesorService.delete(id);
        return Mono.just(ResponseEntity.noContent().build());
    }


}
