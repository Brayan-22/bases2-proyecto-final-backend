package dev.alejandro.sedeservice.config;

import dev.alejandro.sedeservice.event.ProfesorEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class ProfesorPublisherConfig {

    @Bean
    public Sinks.Many<ProfesorEvent> profesorSink() {
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<ProfesorEvent>> profesorSupplier(Sinks.Many<ProfesorEvent> sink) {
        return sink::asFlux;
    }

}
