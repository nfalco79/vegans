package it.nfalco79.example.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.nfalco79.example.api.core.IPolygonModule;
import it.nfalco79.example.api.core.IPolygonValidator;
import it.nfalco79.example.api.dto.PolygonDTO;

class PolygonRegistryTest {

    private PolygonsRegistry sut;

    @BeforeEach
    void setup() {
        sut = new PolygonsRegistry();
    }

    @Test
    void module_with_invalid_name_is_discarded() {
        String moduleName = "  ";

        IPolygonModule module = mock(IPolygonModule.class);
        when(module.getName()).thenReturn(moduleName);

        sut.registerModule(module);
        assertThat(sut.registeredModules()).isEmpty();
    }

    @Test
    void module_is_discarded_when_other_module_was_already_registered() {
        String moduleName = "module1";

        IPolygonModule module1 = mock(IPolygonModule.class);
        when(module1.getName()).thenReturn(moduleName);

        IPolygonModule module2 = mock(IPolygonModule.class);
        when(module2.getName()).thenReturn(moduleName);

        sut.registerModule(module1);
        sut.registerModule(module2);

        assertThat(sut.registeredModules()).hasSize(1);
        assertThat(sut.get(moduleName)) //
                .isSameAs(module1) //
                .isNotSameAs(module2);
    }

    @Test
    void module_is_unregistered_only_if_the_same_that_was_registered() {
        String moduleName = "module1";

        IPolygonModule module1 = mock(IPolygonModule.class);
        when(module1.getName()).thenReturn(moduleName);

        IPolygonModule module2 = mock(IPolygonModule.class);
        when(module2.getName()).thenReturn(moduleName);

        sut.registerModule(module1);
        sut.unregisterModule(module2);

        assertThat(sut.get(moduleName)) //
                .isSameAs(module1) //
                .isNotSameAs(module2);
    }

    @Test
    void unregister_module() {
        String moduleName = "module1";

        IPolygonModule module = mock(IPolygonModule.class);
        when(module.getName()).thenReturn(moduleName);

        sut.registerModule(module);
        sut.unregisterModule(module);

        assertThat(sut.get(moduleName)).isNull();
    }

    @Test
    void validator_delegates_on_module() {
        IPolygonModule module1 = mock(IPolygonModule.class);
        IPolygonValidator module1Validator = buildValidator(false, true);
        when(module1.getName()).thenReturn("module1");
        when(module1.validator()).thenReturn(module1Validator);

        IPolygonModule module2 = mock(IPolygonModule.class);
        IPolygonValidator module2Validator = buildValidator(true, true);
        when(module2.getName()).thenReturn("module2");
        when(module2.validator()).thenReturn(module2Validator);

        sut.registerModule(module1);
        sut.registerModule(module2);

        PolygonDTO input = mock(PolygonDTO.class);
        assertThat(sut.validator().verify(input)).isTrue();
        verify(module1Validator).accept(input);
        verify(module2Validator).accept(input);
        verify(module2Validator).verify(input);
    }

    private IPolygonValidator buildValidator(boolean accept, boolean verify) {
        IPolygonValidator validator = mock(IPolygonValidator.class);
        when(validator.accept(any(PolygonDTO.class))).thenReturn(accept);
        when(validator.verify(any(PolygonDTO.class))).thenReturn(verify);
        return validator;
    }
}
