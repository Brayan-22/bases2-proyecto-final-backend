package dev.alejandro.centralservice.config;

import dev.alejandro.centralservice.event.CentralResponseEvent;
import dev.alejandro.centralservice.event.OperationStatus;
import dev.alejandro.centralservice.event.ProfesorEvent;
import dev.alejandro.centralservice.repository.KafkaUpdateRepository;
import dev.alejandro.centralservice.repository.ProfesorRepository;
import dev.alejandro.centralservice.service.IProfesorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Schedules;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.function.Function;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ProfesorProcessorConfig {

    private final IProfesorService profesorService;
    private final KafkaUpdateRepository profesorRepository;
    @Bean
    public Function<Flux<ProfesorEvent>, Flux<CentralResponseEvent>> profesorProcessor() {
        return eventFlux -> eventFlux.flatMap(this::procesarEventoProfesor);
    }

    public Mono<CentralResponseEvent> procesarEventoProfesor(ProfesorEvent profesorEvent) {
        return Mono.defer(() -> { 
            log.info(" Procesando evento: {}", profesorEvent);

            try {
                return switch (profesorEvent.getOperation()) {
                    case CREATE -> Mono.fromCallable(()->{
                        profesorRepository.enableKafkaDelete();
                        profesorService.save(profesorEvent.getProfesor());
                        profesorRepository.disableKafkaDelete();
                        return new CentralResponseEvent(OperationStatus.SUCCESS);
                    }).subscribeOn(Schedulers.boundedElastic());
                    case UPDATE -> Mono.fromCallable(()->{
                        profesorRepository.enableKafkaDelete();
                        profesorService.update(profesorEvent.getProfesor().getDocProfesor(), profesorEvent.getProfesor());
                        profesorRepository.disableKafkaDelete();
                        return new CentralResponseEvent(OperationStatus.SUCCESS);
                    }).subscribeOn(Schedulers.boundedElastic());
                    case DELETE -> Mono.fromCallable(()->{
                        profesorRepository.enableKafkaDelete();
                        profesorService.delete(profesorEvent.getProfesor().getDocProfesor());
                        profesorRepository.disableKafkaDelete();
                        return new CentralResponseEvent(OperationStatus.SUCCESS);
                    }).subscribeOn(Schedulers.boundedElastic());
                };
            } catch (RuntimeException e) {
                log.error("Error al procesar evento", e);
                return Mono.just(new CentralResponseEvent(OperationStatus.ERROR));
            }
        });
    }
}