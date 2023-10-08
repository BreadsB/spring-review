package com.breadsb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@OpenAPIDefinition( info = @Info(title = "Note Api", version = "1.0", description = "Documentation Notes API v1.0"))
public class NotesSpringApp {

    public static void main(String[] args) {
        SpringApplication.run(NotesSpringApp.class ,args);
        System.out.println("classpath:/src/");
    }
}