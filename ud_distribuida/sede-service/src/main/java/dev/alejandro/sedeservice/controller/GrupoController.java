package dev.alejandro.sedeservice.controller;

import dev.alejandro.sedeservice.dto.CreateGrupoRequestDto;
import dev.alejandro.sedeservice.dto.GrupoResponseDto;
import dev.alejandro.sedeservice.entity.GrupoId;
import dev.alejandro.sedeservice.service.IGrupoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/grupo")
public class GrupoController {

    private final IGrupoService grupoService;
    @PostMapping
    public Mono<ResponseEntity<GrupoResponseDto>> save(@Valid@RequestBody CreateGrupoRequestDto requestDto) {
        return Mono.just(ResponseEntity.ok(grupoService.save(requestDto)));
    }

    @GetMapping("/all")
    public Mono<ResponseEntity<List<GrupoResponseDto>>> getAll(){
        return Mono.just(ResponseEntity.ok(grupoService.findAll()));
    }
    @GetMapping("/codGrupo/{codGrupo}")
    public Mono<ResponseEntity<List<GrupoResponseDto>>> findByCodGrupo(@PathVariable String codGrupo){
        return Mono.just(ResponseEntity.ok(grupoService.findByCodGrupo(codGrupo)));
    }
    @GetMapping("/periodo/{periodo}")
    public Mono<ResponseEntity<List<GrupoResponseDto>>> findByPeriodoGrupo(@PathVariable String periodo){
        return Mono.just(ResponseEntity.ok(grupoService.findByPeriodoGrupo(periodo)));
    }
    @PutMapping
    public Mono<ResponseEntity<GrupoResponseDto>> update(@RequestParam String codGrupo,
            @RequestParam String periodoGrupo,
            @Valid@RequestBody CreateGrupoRequestDto requestDto){
        return Mono.just(ResponseEntity.ok(grupoService.update(new GrupoId(codGrupo,periodoGrupo),requestDto)));
    }
    @DeleteMapping
    public Mono<ResponseEntity<Void>> delete(@RequestParam String codGrupo, @RequestParam String periodoGrupo){
        grupoService.deleteByCodGrupo(new GrupoId(codGrupo,periodoGrupo));
        return Mono.just(ResponseEntity.noContent().build());
    }

}
