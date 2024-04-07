package it.nfalco79.example.api.core.spi;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import it.nfalco79.example.api.core.IPolygonModule;
import it.nfalco79.example.api.core.IPolygonValidator;

/**
 * The implementation of this registry contains all registered
 * {@link IPolygonModule}.
 *
 * @author NikolasFalco
 */
public interface IPolygonsRegistry {

    @Nullable
    IPolygonModule get(String name);

    /**
     * Register a new {@link IPolygonModule}.
     * <p>
     * If a module with the same name was already registered than the current
     * module is discarded.
     *
     * @param module to register
     */
    void registerModule(@NonNull IPolygonModule module);

    /**
     * Unregister a the given {@link IPolygonModule} from the registry.
     * <p>
     * If another module was registered with the same name, the registered
     * module is left untouched and the given module ignored.
     *
     * @param module to unregister
     */
    void unregisterModule(@NonNull IPolygonModule module);

    /**
     * Returns a polygon validator for the current registered module.
     * <p>
     * Every time a new module is registered should be request a new instance of
     * validator.
     *
     * @return a polygon validator
     */
    IPolygonValidator validator();

}