package it.nfalco79.example.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import it.nfalco79.example.api.core.ExampleServiceRuntimeException;
import it.nfalco79.example.api.core.IPolygonModule;
import it.nfalco79.example.api.core.IPolygonValidator;
import it.nfalco79.example.api.core.spi.IPolygonsRegistry;
import it.nfalco79.example.api.dto.PolygonDTO;

class PolygonServiceTest {

    private PolygonService sut;
    private IPolygonsRegistry registry;
    private IPolygonModule module;

    @BeforeEach
    void setup() {
        IPolygonValidator validator = mock(IPolygonValidator.class);
        when(validator.accept(any(PolygonDTO.class))).thenReturn(true);
        when(validator.verify(any(PolygonDTO.class))).thenReturn(true);

        module = mock(IPolygonModule.class);
        when(module.validator()).thenReturn(validator);

        registry = mock(IPolygonsRegistry.class);
        sut = new PolygonService(registry);
    }

    @Test
    void test_area() {
        double expectedArea = 4d;
        PolygonDTO input = buildPolygon();

        when(module.area(any(PolygonDTO.class))).thenReturn(expectedArea);
        when(registry.get(input.getName())).thenReturn(module);

        assertThat(sut.area(input)).isEqualTo(expectedArea);

        ArgumentCaptor<PolygonDTO> captor = ArgumentCaptor.forClass(PolygonDTO.class);
        verify(module).area(captor.capture());
        assertThat(captor.getValue()).isEqualTo(input);
    }

    @Test
    void test_perimeter() {
        double expectedPerimeter = 4d;
        PolygonDTO input = buildPolygon();

        when(module.perimeter(any(PolygonDTO.class))).thenReturn(expectedPerimeter);
        when(registry.get(input.getName())).thenReturn(module);

        assertThat(sut.perimeter(input)).isEqualTo(expectedPerimeter);

        ArgumentCaptor<PolygonDTO> captor = ArgumentCaptor.forClass(PolygonDTO.class);
        verify(module).perimeter(captor.capture());
        assertThat(captor.getValue()).isEqualTo(input);
    }

    @Test
    void area_throw_exception_when_polygon_is_not_valid() {
        PolygonDTO input = buildPolygon();

        when(module.validator().verify(input)).thenReturn(false);
        when(registry.get(input.getName())).thenReturn(module);

        assertThatThrownBy(() -> sut.area(input)) //
                .isInstanceOf(ExampleServiceRuntimeException.class);
    }

    @Test
    void perimeter_throw_exception_when_polygon_is_not_valid() {
        PolygonDTO input = buildPolygon();

        when(module.validator().verify(input)).thenReturn(false);
        when(registry.get(input.getName())).thenReturn(module);

        assertThatThrownBy(() -> sut.perimeter(input)) //
                .isInstanceOf(ExampleServiceRuntimeException.class);
    }

    @Test
    void area_and_perimeter_throw_exception_when_polygon_has_no_module() {
        PolygonDTO input = buildPolygon();

        assertThatThrownBy(() -> sut.area(input)) //
                .isInstanceOf(ExampleServiceRuntimeException.class);
        assertThatThrownBy(() -> sut.perimeter(input)) //
                .isInstanceOf(ExampleServiceRuntimeException.class);
    }

    private PolygonDTO buildPolygon() {
        PolygonDTO polygon = new PolygonDTO();
        polygon.setName("test");
        polygon.getSegments().add(3d);
        return polygon;
    }
}
