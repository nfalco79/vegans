package it.nfalco79.example.modules;

import org.springframework.util.Assert;

import edu.umd.cs.findbugs.annotations.NonNull;
import it.nfalco79.example.api.dto.PolygonDTO;

public class CircleModule extends AbstractPolygonModule {
    private static final String MODULE_NAME = "CIRCLE";

    public CircleModule() {
        super(1);
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @Override
    public double area(@NonNull PolygonDTO polygon) {
        Assert.notNull(polygon, "polygon is null");

        Double radius = getRadius(polygon);
        return Math.PI * Math.pow(radius, 2);
    }

    @NonNull
    private Double getRadius(@NonNull PolygonDTO polygon) {
        Double radius = polygon.getSegments().get(0);
        return radius == null ? 0 : radius; // NOSONAR
    }

    @Override
    public double perimeter(@NonNull PolygonDTO polygon) {
        return 2d * Math.PI * getRadius(polygon);
    }

}