package kkol.crc.careg.registration;

import kkol.crc.careg.dto.CarDTO;
import kkol.crc.careg.model.Car;
import kkol.crc.careg.model.FuelType;
import kkol.crc.careg.model.User;
import kkol.crc.careg.repository.CarRepository;
import kkol.crc.careg.repository.UserRepository;
import kkol.crc.careg.service.CarService;
import kkol.crc.careg.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        carService = new CarServiceImpl(carRepository, userRepository);

    }

    @Test
    public void checkAddCarTest() {
        // given
        CarDTO carDTO = CarDTO.builder()
                .model("test_model")
                .mark("test_mark")
                .productionYear(1990)
                .enginePower(32f)
                .fuelType(FuelType.BIODIESEL.name())
                .weight(123f)
                .build();

        given(carRepository.save(any())).will(returnsFirstArg());


        // when
        Car car = carService.registerCar(carDTO);

        //then
        then(car.isInParentDatabase()).isFalse();
        verify(userRepository).save(new User("default_user"));
        verify(carRepository).save(car);
    }
}
