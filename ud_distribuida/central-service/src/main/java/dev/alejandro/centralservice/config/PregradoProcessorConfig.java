package dev.alejandro.centralservice.config;

import dev.alejandro.centralservice.dto.UpdatePregradoRequestDto;
import dev.alejandro.centralservice.event.CentralResponseEvent;
import dev.alejandro.centralservice.event.OperationStatus;
import dev.alejandro.centralservice.event.PregradoEvent;
import dev.alejandro.centralservice.repository.KafkaUpdateRepository;
import dev.alejandro.centralservice.repository.PregradoRepository;
import dev.alejandro.centralservice.service.IPregradoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.function.Function;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class PregradoProcessorConfig {
    private final IPregradoService pregradoService;
    private final KafkaUpdateRepository pregradoRepository;
    @Bean
    public Function<Flux<PregradoEvent>,Flux<CentralResponseEvent>> pregradoProcessor() {
        return eventFlux -> eventFlux.flatMap(this::procesarEventoPregrado);
    }

    public Mono<CentralResponseEvent> procesarEventoPregrado(PregradoEvent profesorEvent) {
        return Mono.defer(() -> {
            log.info(" Procesando evento: {}", profesorEvent);

            try {
                return switch (profesorEvent.getOperation()) {
                    case CREATE -> Mono.fromCallable(() -> {
                        pregradoRepository.enableKafkaDelete();
                        pregradoService.createPregrado(profesorEvent.getCreatePregradoRequestDto());
                        pregradoRepository.disableKafkaDelete();
                        return new CentralResponseEvent(OperationStatus.SUCCESS);
                    }).subscribeOn(Schedulers.boundedElastic());
                    case UPDATE -> Mono.fromCallable(()->{
                        pregradoRepository.enableKafkaDelete();
                        pregradoService.updatePregrado(profesorEvent.getCreatePregradoRequestDto().getCodPregrado(),
                                new UpdatePregradoRequestDto(profesorEvent.getCreatePregradoRequestDto().getCodPregrado(),
                                        profesorEvent.getCreatePregradoRequestDto().getNombre(),
                                        profesorEvent.getCreatePregradoRequestDto().getCreditos(),
                                        profesorEvent.getCreatePregradoRequestDto().getNotaMinima(),
                                        profesorEvent.getCreatePregradoRequestDto().getCorreo(),
                                        profesorEvent.getCreatePregradoRequestDto().getSede()));
                        pregradoRepository.disableKafkaDelete();
                        return new CentralResponseEvent(OperationStatus.SUCCESS);
                    }).subscribeOn(Schedulers.boundedElastic());
                    case DELETE -> Mono.fromCallable(()->{
                        pregradoRepository.enableKafkaDelete();
                        pregradoService.deletePregrado(profesorEvent.getCreatePregradoRequestDto().getCodPregrado());
                        pregradoRepository.disableKafkaDelete();
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
