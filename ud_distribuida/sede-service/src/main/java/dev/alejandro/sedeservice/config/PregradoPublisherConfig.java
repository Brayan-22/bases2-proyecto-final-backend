package dev.alejandro.sedeservice.config;

import dev.alejandro.sedeservice.event.PregradoEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class PregradoPublisherConfig {
    @Bean
    public Sinks.Many<PregradoEvent> pregradoSink() {
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<PregradoEvent>> pregradoSupplier(Sinks.Many<PregradoEvent> sink) {
        return sink::asFlux;
    }
}
