package it.nfalco79.example.core.internal;

import org.springframework.lang.Nullable;

import it.nfalco79.example.api.core.ExampleServiceRuntimeException;

public final class CoreExceptionFactory {
    private CoreExceptionFactory() {
    }

    public static ExampleServiceRuntimeException getPolygonTypeNotSupported(@Nullable String polygonName) {
        return new ExampleServiceRuntimeException(CoreConsts.POLYGON_NOT_SUPPORTED, "Polygon " + polygonName + " not supported");
    }

    public static ExampleServiceRuntimeException getPolygonTypeNotSupported(int segmentCount) {
        return new ExampleServiceRuntimeException(CoreConsts.POLYGON_INVALID_SEGMENT_COUNT, "The number of segment " + segmentCount + " is not valid");
    }
}
