package it.nfalco79.example.api.core;

import edu.umd.cs.findbugs.annotations.NonNull;
import it.nfalco79.example.api.dto.PolygonDTO;

/**
 * New polygon must implement of this interface to provide some mathematics
 * operations.
 *
 * @author NikolasFalco
 */
public interface IPolygonModule {

    /**
     * Returns the name of this mathematics module. This name must be unique.
     *
     * @return name of the implementation module
     */
    String getName();

    /**
     * Returns the area for the given polygon.
     *
     * @param polygon data, for polygons of kind circle segments must contain
     *        radiuses values. polygon must not be null.
     * @return the area of the polygon
     */
    double area(@NonNull PolygonDTO polygon);

    /**
     * Returns the perimeter for the given polygon.
     *
     * @param polygon data, for polygons of kind circle segments must contain
     *        radiuses values. polygon must not be null.
     * @return the area of the polygon
     */
    double perimeter(@NonNull PolygonDTO polygon);

    IPolygonValidator validator();
}
