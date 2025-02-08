package dev.alejandro.centralservice.controller;

import dev.alejandro.centralservice.dto.DictarRequestDto;
import dev.alejandro.centralservice.dto.DictarResponseDto;
import dev.alejandro.centralservice.entity.DictarId;
import dev.alejandro.centralservice.entity.GrupoId;
import dev.alejandro.centralservice.service.IDictarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/dictar")
@RequiredArgsConstructor
public class DictarController {
    private final IDictarService dictarService;

    @PostMapping
    public Mono<ResponseEntity<DictarResponseDto>> crateDictar(@RequestBody DictarRequestDto requestDto){
        return Mono.just(ResponseEntity.ok(dictarService.save(requestDto)));
    }

    @PutMapping
    public Mono<ResponseEntity<DictarResponseDto>> updateDictar(
            @RequestParam String docProfesor,
            @RequestParam String codGrupo,
            @RequestParam String periodoGrupo,
            @RequestBody DictarRequestDto requestDto){
        return Mono.just(ResponseEntity.ok(dictarService.update(new DictarId(docProfesor,new GrupoId(codGrupo,periodoGrupo)),requestDto)));
    }
    @GetMapping("/all")
    public Mono<ResponseEntity<List<DictarResponseDto>>> getAll(){
        return Mono.just(ResponseEntity.ok(dictarService.findAll()));
    }

    @DeleteMapping
    public Mono<ResponseEntity<?>> delete(
            @RequestParam String docProfesor,
            @RequestParam String codGrupo,
            @RequestParam String periodoGrupo
    ){
        dictarService.deleteByCodDictar(new DictarId(docProfesor,new GrupoId(codGrupo,periodoGrupo)));
        return Mono.just(ResponseEntity.noContent().build());

    }

}
