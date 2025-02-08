package dev.alejandro.centralservice.controller;

import dev.alejandro.centralservice.dto.CreatePagoNominaRequestDto;
import dev.alejandro.centralservice.dto.PagoNominaResponseDto;
import dev.alejandro.centralservice.service.INominaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/nomina")
@RequiredArgsConstructor
public class NominaController {

    private final INominaService nominaService;

    @PostMapping
    public Mono<ResponseEntity<PagoNominaResponseDto>> pagarNomina(@Valid @RequestBody CreatePagoNominaRequestDto request) {
        return Mono.just(ResponseEntity.ok(nominaService.pagarNomina(request)));
    }

    @GetMapping("/all")
    public Mono<ResponseEntity<List<PagoNominaResponseDto>>> listarPagos() {
        return Mono.just(ResponseEntity.ok(nominaService.listarPagos()));
    }
}
