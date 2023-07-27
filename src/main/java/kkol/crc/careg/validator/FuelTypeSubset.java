package kkol.crc.careg.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import kkol.crc.careg.model.FuelType;

import java.lang.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FuelTypeSubsetValidator.class)
public @interface FuelTypeSubset {

    FuelType[] anyOf();
    Class<? extends Enum<?>> enumClass();
    String message() default "Must be any of enum {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
