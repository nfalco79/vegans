package it.nfalco79.example.api.dto;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import it.nfalco79.example.api.core.spi.IPolygonsRegistry;

/**
 * The annotated element must be a valid polygon. Each module registered in the
 * {@link IPolygonsRegistry} can contribute with custom checks.
 */
@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.TYPE })
@Retention(RUNTIME)
public @interface Polygon {
    String message() default "{it.nfalco79.example.api.dto.Polygon.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
