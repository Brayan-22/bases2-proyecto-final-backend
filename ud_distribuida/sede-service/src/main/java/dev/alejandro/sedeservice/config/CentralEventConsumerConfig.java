package dev.alejandro.sedeservice.config;

import dev.alejandro.sedeservice.event.CentralResponseEvent;
import dev.alejandro.sedeservice.event.OperationStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class CentralEventConsumerConfig {
    @Bean
    public Consumer<Flux<CentralResponseEvent>> centralEventConsumer(){
        return eventFlux -> eventFlux.subscribe(response -> {
            if (response.getStatus().equals(OperationStatus.SUCCESS)) {
                log.info("Evento procesado con exito");
            }else{
                log.error("Error al procesar evento");
            }
        });
    }
}
