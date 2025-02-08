package dev.alejandro.centralservice;

import dev.alejandro.centralservice.entity.Clasificacion;
import dev.alejandro.centralservice.entity.ClasificacionEnum;
import dev.alejandro.centralservice.repository.ClasificacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class CentralServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CentralServiceApplication.class);
    }
}
