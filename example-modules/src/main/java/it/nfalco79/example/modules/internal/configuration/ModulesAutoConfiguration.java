package it.nfalco79.example.modules.internal.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

import it.nfalco79.example.api.core.IPolygonModule;
import it.nfalco79.example.api.core.spi.IPolygonsRegistry;
import it.nfalco79.example.modules.CircleModule;
import it.nfalco79.example.modules.EllipseModule;
import it.nfalco79.example.modules.SemicircleModule;
import it.nfalco79.example.modules.TriangleModule;

@AutoConfiguration
@ConditionalOnClass(IPolygonsRegistry.class)
public class ModulesAutoConfiguration {

    @Bean
    IPolygonModule triangle(IPolygonsRegistry registry) {
        IPolygonModule module = new TriangleModule();
        registry.registerModule(module);
        return module;
    }

    @Bean
    IPolygonModule circle(IPolygonsRegistry registry) {
        IPolygonModule module = new CircleModule();
        registry.registerModule(module);
        return module;
    }

    @Bean
    IPolygonModule ellipse(IPolygonsRegistry registry) {
        IPolygonModule module = new EllipseModule();
        registry.registerModule(module);
        return module;
    }

    @Bean
    IPolygonModule semicircle(IPolygonsRegistry registry) {
        IPolygonModule module = new SemicircleModule();
        registry.registerModule(module);
        return module;
    }
}
