package it.nfalco79.example.modules;

import org.springframework.util.Assert;

import edu.umd.cs.findbugs.annotations.NonNull;
import it.nfalco79.example.api.core.IPolygonModule;
import it.nfalco79.example.api.core.IPolygonValidator;
import it.nfalco79.example.api.dto.PolygonDTO;

abstract class AbstractPolygonModule implements IPolygonModule {

    private final int expectedSegments;

    protected AbstractPolygonModule(int expectedSegments) {
        this.expectedSegments = expectedSegments;
    }

    @Override
    public double perimeter(@NonNull PolygonDTO polygon) {
        Assert.notNull(polygon, "polygon is null");

        return polygon.getSegments().stream().reduce(0d, Double::sum);
    }

    @Override
    public IPolygonValidator validator() {
        return new IPolygonValidator() {
            @Override
            public boolean accept(@NonNull PolygonDTO polygon) {
                return getName().equalsIgnoreCase(polygon.getName());
            }

            @Override
            public boolean verify(@NonNull PolygonDTO polygon) {
                int segmentCount = polygon.getSegments().size();
                return segmentCount == expectedSegments;
            }
        };
    }
}
