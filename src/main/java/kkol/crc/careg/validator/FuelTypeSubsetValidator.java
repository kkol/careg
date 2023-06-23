package kkol.crc.careg.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kkol.crc.careg.model.FuelType;

import java.util.Arrays;

public class FuelTypeSubsetValidator implements ConstraintValidator<FuelTypeSubset, FuelType> {
    private FuelType[] subset;

    @Override
    public void initialize(FuelTypeSubset constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(FuelType value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(subset).contains(value);
    }

}
