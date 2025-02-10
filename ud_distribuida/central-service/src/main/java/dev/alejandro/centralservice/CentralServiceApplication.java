package dev.alejandro.centralservice;

import dev.alejandro.centralservice.repository.KafkaUpdateRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class CentralServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CentralServiceApplication.class);
    }

    @Bean
    ApplicationRunner applicationRunner(KafkaUpdateRepository kafkaUpdateRepository) {
        return args -> {
            kafkaUpdateRepository.disableKafkaDelete();
        };
    }
}
