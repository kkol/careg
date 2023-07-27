package kkol.crc.careg.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kkol.crc.careg.model.FuelType;
import kkol.crc.careg.validator.FuelTypeSubset;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.Arrays;


@Value
@Data
@Builder
public class CarDTO {

    @NotBlank(message = "Mark should not be empty")
    @NotNull(message = "Mark should not be null")
    String mark;

    @NotBlank(message = "Model should not be empty")
    @NotNull(message = "Model should not be null")
    String model;
    @Positive(message = "Should be grater than zero")
    int productionYear;
    @Positive(message ="Should be grater than zero")
    float enginePower, weight;

    @FuelTypeSubset(enumClass = FuelType.class, anyOf = {FuelType.DIESEL, FuelType.BIODIESEL, FuelType.ELECTRIC, FuelType.ETHANOL, FuelType.GASOLINE})
    String fuelType;
}
