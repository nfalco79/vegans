package it.nfalco79.example.core.internal.configuration;

import org.springframework.context.annotation.Bean;

import it.nfalco79.example.api.core.spi.IPolygonsRegistry;
import it.nfalco79.example.core.service.PolygonService;
import it.nfalco79.example.core.service.PolygonsRegistry;

public class CoreServiceConfiguration {

    @Bean
    IPolygonsRegistry registry() {
        return new PolygonsRegistry();
    }

    @Bean
    PolygonService polygonService(IPolygonsRegistry regstry) {
        return new PolygonService(regstry);
    }
}
