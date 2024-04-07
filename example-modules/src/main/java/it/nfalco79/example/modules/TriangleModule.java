package it.nfalco79.example.modules;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import it.nfalco79.example.api.dto.PolygonDTO;

public class TriangleModule extends AbstractPolygonModule {
    private static final String MODULE_NAME = "TRIANGLE";

    public TriangleModule() {
        super(3);
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @Override
    public double area(@NonNull PolygonDTO polygon) {
        Assert.notNull(polygon, "polygon is null");

        double p = perimeter(polygon);
        Double a = polygon.getSegments().get(0);
        Double b = polygon.getSegments().get(1);
        Double c = polygon.getSegments().get(2);
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

}