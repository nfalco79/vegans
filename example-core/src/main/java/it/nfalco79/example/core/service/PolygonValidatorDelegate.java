package it.nfalco79.example.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.umd.cs.findbugs.annotations.NonNull;
import it.nfalco79.example.api.core.IPolygonValidator;
import it.nfalco79.example.api.dto.PolygonDTO;

class PolygonValidatorDelegate implements IPolygonValidator {

    private final List<IPolygonValidator> decorators;

    public PolygonValidatorDelegate(Collection<IPolygonValidator> decorators) {
        this.decorators = new ArrayList<>(decorators);
    }

    @Override
    public boolean accept(@NonNull PolygonDTO polygon) {
        return decorators.stream() //
                .anyMatch(decorator -> decorator.accept(polygon));
    }

    @Override
    public boolean verify(@NonNull PolygonDTO polygon) {
        return decorators.stream() //
                .filter(decorator -> decorator.accept(polygon)) //
                .findFirst() //
                .map(decorator -> decorator.verify(polygon)) //
                .orElse(true);
    }

}
