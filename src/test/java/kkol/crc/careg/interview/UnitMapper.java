package kkol.crc.careg.interview;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UnitMapper {


    public float celsiusToFahrenheit(final float celsius) {
        return ((celsius*9)/5)+32;
    }

    public float fahrenheitToCelsius(final float fahrenheit) {
        return ((fahrenheit-32)*5)/9;
    }




}
