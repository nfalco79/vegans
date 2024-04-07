package it.nfalco79.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import it.nfalco79.example.core.internal.configuration.CoreConfiguration;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAutoConfiguration
@Import({
    CoreConfiguration.class,
})
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
