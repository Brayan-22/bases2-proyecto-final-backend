package dev.alejandro.centralservice.controller;

import dev.alejandro.centralservice.dto.CreatePregradoRequestDto;
import dev.alejandro.centralservice.dto.PregradoResponseDto;
import dev.alejandro.centralservice.dto.UpdatePregradoRequestDto;
import dev.alejandro.centralservice.service.IPregradoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/pregrado")
@RequiredArgsConstructor
@Slf4j
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
    public Mono<ResponseEntity<PregradoResponseDto>> getPregradoById(@PathVariable Integer id){
        return Mono.just(ResponseEntity.ok(pregradoService.getPregradoById(id)));
    }

    @GetMapping
    public Mono<ResponseEntity<PregradoResponseDto>> getPregradoByNombre(@RequestParam String nombre){
        return Mono.just(ResponseEntity.ok(pregradoService.getPregradoByName(nombre)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> deletePregrado(@PathVariable Integer id){
        pregradoService.deletePregrado(id);
        return Mono.just(ResponseEntity.ok("Pregrado eliminado"));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<PregradoResponseDto>> updatePregrado(@PathVariable Integer id, @Valid @RequestBody UpdatePregradoRequestDto requestDto){
        return Mono.just(ResponseEntity.ok(pregradoService.updatePregrado(id, requestDto)));
    }

}
