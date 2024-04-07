package it.nfalco79.example.core.service;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import it.nfalco79.example.api.core.IPolygonModule;
import it.nfalco79.example.api.core.spi.IPolygonsRegistry;
import it.nfalco79.example.api.dto.PolygonDTO;
import it.nfalco79.example.core.internal.CoreExceptionFactory;

public class PolygonService {
    private final IPolygonsRegistry registry;

    public PolygonService(@NonNull IPolygonsRegistry registry) {
        Assert.notNull(registry, "registry is null");

        this.registry = registry;
    }

    public double perimeter(@NonNull PolygonDTO polygon) {
        Assert.notNull(polygon, "polygon is null");

        IPolygonModule module = getModule(polygon);
        if (!module.validator().verify(polygon)) {
            throw CoreExceptionFactory.getPolygonTypeNotSupported(polygon.getSegments().size());
        }
        return module.perimeter(polygon);
    }

    public double area(@NonNull PolygonDTO polygon) {
        Assert.notNull(polygon, "polygon is null");

        IPolygonModule module = getModule(polygon);
        if (!module.validator().verify(polygon)) {
            throw CoreExceptionFactory.getPolygonTypeNotSupported(polygon.getSegments().size());
        }
        return module.area(polygon);
    }

    @NonNull
    private IPolygonModule getModule(PolygonDTO polygon) {
        IPolygonModule module = registry.get(polygon.getName());
        if (module == null) {
            throw CoreExceptionFactory.getPolygonTypeNotSupported(polygon.getName());
        }
        return module;
    }

}
