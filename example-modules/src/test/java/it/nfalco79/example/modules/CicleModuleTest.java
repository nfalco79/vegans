package it.nfalco79.example.modules;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.nfalco79.example.api.core.IPolygonValidator;
import it.nfalco79.example.api.dto.PolygonDTO;

class CicleModuleTest {

    private CircleModule sut;

    @BeforeEach
    void setup() {
        sut = new CircleModule();
    }

    @Test
    void verify_area() {
        PolygonDTO input = buildPolygon();
        assertThat(sut.area(input)).isEqualTo(28.274333882308138d);
    }

    @Test
    void verify_perimeter() {
        PolygonDTO input = buildPolygon();
        assertThat(sut.perimeter(input)).isEqualTo(18.84955592153876d);
    }

    @Test
    void test_validator() {
        IPolygonValidator validator = sut.validator();

        PolygonDTO input = buildPolygon();
        assertThat(validator.verify(input)).isTrue();
        assertThat(validator.accept(input)).isTrue();

        input.getSegments().add(1d);
        assertThat(validator.verify(input)).isFalse();
        input.setName("aa");
        assertThat(validator.accept(input)).isFalse();
    }

    private PolygonDTO buildPolygon() {
        PolygonDTO polygon = new PolygonDTO();
        polygon.setName(sut.getName());
        polygon.getSegments().add(3d);
        return polygon;
    }
}
