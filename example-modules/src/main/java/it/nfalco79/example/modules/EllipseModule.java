package it.nfalco79.example.modules;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import it.nfalco79.example.api.dto.PolygonDTO;

public class EllipseModule extends AbstractPolygonModule {
    private enum SemiAxe {
        MINOR, MAJOR
    }

    private static final String MODULE_NAME = "ELLIPSE";

    public EllipseModule() {
        super(2);
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @Override
    public double area(@NonNull PolygonDTO polygon) {
        Assert.notNull(polygon, "polygon is null");

        Double semiMajorAxe = getSemiAxe(polygon, SemiAxe.MAJOR);
        Double semiMinorAxe = getSemiAxe(polygon, SemiAxe.MINOR);
        return 2d * Math.PI * Math.sqrt((Math.pow(semiMajorAxe, 2) + Math.pow(semiMinorAxe, 2)) / 2d);
    }

    @NonNull
    private Double getSemiAxe(@NonNull PolygonDTO polygon, @NonNull SemiAxe type) {
        Double semiAxe = null;
        if (type == SemiAxe.MINOR) {
            semiAxe = polygon.getSegments().get(0);
        } else {
            semiAxe = polygon.getSegments().get(1);
        }
        return semiAxe == null ? 0 : semiAxe; // NOSONAR
    }

    @Override
    public double perimeter(PolygonDTO polygon) {
        Double semiMajorAxe = getSemiAxe(polygon, SemiAxe.MAJOR);
        Double semiMinorAxe = getSemiAxe(polygon, SemiAxe.MINOR);
        return Math.PI * semiMajorAxe * semiMinorAxe;
    }

}