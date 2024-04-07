package it.nfalco79.example.api.core;

import edu.umd.cs.findbugs.annotations.NonNull;
import it.nfalco79.example.api.dto.PolygonDTO;

/**
 * The implementation of this validator must check if the polygon have all
 * required parameters.
 */
public interface IPolygonValidator {

    /**
     * Returns if this validator can be applied to polygon.
     *
     * @param polygon to accept by the validator
     * @return {@code true} is this validator is designed for this polygon,
     *         {@code false} otherwise
     */
    boolean accept(@NonNull PolygonDTO polygon);

    /**
     * Returns if the polygon.
     *
     * @param polygon to verify by the validator
     * @return {@code true} if is a valid polygon, {@code false} otherwise
     */
    boolean verify(@NonNull PolygonDTO polygon);
}
