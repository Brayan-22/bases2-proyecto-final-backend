package dev.alejandro.sedeservice.controller;


import dev.alejandro.sedeservice.dto.CreatePregradoRequestDto;
import dev.alejandro.sedeservice.dto.PregradoResponseDto;
import dev.alejandro.sedeservice.dto.UpdatePregradoRequestDto;
import dev.alejandro.sedeservice.service.IPregradoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/pregrado")
@RequiredArgsConstructor
public class PregradoController {
    private final IPregradoService pregradoService;

    @PostMapping
    public Mono<ResponseEntity<PregradoResponseDto>> createPregrado(
            @Valid @RequestBody Mono<CreatePregradoRequestDto> requestDto) {
        return requestDto.map(dto ->
                ResponseEntity.ok(pregradoService.createPregrado(dto))
        );
    }
    @GetMapping("/all")
    public Mono<ResponseEntity<List<PregradoResponseDto>>> getPregrados(){
        return Mono.just(ResponseEntity.ok(pregradoService.getAllPregrados()));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PregradoResponseDto>> getPregradoById(@PathVariable String id){
        return Mono.just(ResponseEntity.ok(pregradoService.getPregradoById(id)));
    }

    @GetMapping
    public Mono<ResponseEntity<PregradoResponseDto>> getPregradoByNombre(@RequestParam String nombre){
        return Mono.just(ResponseEntity.ok(pregradoService.getPregradoByName(nombre)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> deletePregrado(@PathVariable String id){
        pregradoService.deletePregrado(id);
        return Mono.just(ResponseEntity.ok("Pregrado eliminado"));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<PregradoResponseDto>> updatePregrado(@PathVariable String id, @Valid @RequestBody UpdatePregradoRequestDto requestDto){
        return Mono.just(ResponseEntity.ok(pregradoService.updatePregrado(id, requestDto)));
    }
}
