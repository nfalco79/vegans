package it.nfalco79.example.core.service;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import it.nfalco79.example.api.core.IPolygonModule;
import it.nfalco79.example.api.core.IPolygonValidator;
import it.nfalco79.example.api.core.spi.IPolygonsRegistry;

public final class PolygonsRegistry implements IPolygonsRegistry {
    private static final Logger LOGGER = LoggerFactory.getLogger(PolygonsRegistry.class);

    private Map<String, IPolygonModule> modules = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    @Override
    @Nullable
    public IPolygonModule get(@NonNull String name) {
        Assert.hasText(name, "name is null or empty");

        return modules.get(trimToEmpty(name));
    }

    @Override
    public void registerModule(@NonNull IPolygonModule module) {
        Assert.notNull(module, "module is null");

        if (isBlank(module.getName())) {
            LOGGER.warn("Module {} has not been registered", module.getName());
            return;
        }

        modules.computeIfAbsent(module.getName(), key -> module);
    }

    @Override
    public void unregisterModule(@NonNull IPolygonModule module) {
        Assert.notNull(module, "module is null");
        Assert.hasText(module.getName(), "module.name is empty");

        if (!modules.remove(module.getName(), module)) {
            LOGGER.warn("No module {} has been found or another implementations was registered with the same name", module.getName());
        }
    }

    @Override
    public IPolygonValidator validator() {
        List<IPolygonValidator> validators = modules.values().stream() //
                .map(IPolygonModule::validator) //
                .collect(Collectors.toList());
        return new PolygonValidatorDelegate(validators);
    }

    /**
     * For test purpose.
     *
     * @apiNote this is NOT an API
     * @return all registered module name
     */
    Set<String> registeredModules() {
        return modules.keySet();
    }
}
