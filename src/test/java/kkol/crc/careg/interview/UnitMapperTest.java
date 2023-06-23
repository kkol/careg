package kkol.crc.careg.interview;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
public class UnitMapperTest {

    private final UnitMapper unitMapper = new UnitMapper();

    @Test
    public void checkCelsiusToFarenhait(){
        float celsius = 1f;
        then(unitMapper.celsiusToFahrenheit(celsius)).isEqualTo(32);
    }
}
