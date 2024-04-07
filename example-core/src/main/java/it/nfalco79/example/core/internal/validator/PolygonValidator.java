package it.nfalco79.example.core.internal.validator;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import it.nfalco79.example.api.core.spi.IPolygonsRegistry;
import it.nfalco79.example.api.dto.Polygon;
import it.nfalco79.example.api.dto.PolygonDTO;

public class PolygonValidator implements ConstraintValidator<Polygon, PolygonDTO> {

    @Inject
    private IPolygonsRegistry registry;

    @Override
    public boolean isValid(PolygonDTO segments, ConstraintValidatorContext context) {
        return registry.validator().verify(segments);
    }

}
