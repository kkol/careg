package kkol.crc.careg.registration;

import kkol.crc.careg.dto.CarDTO;
import kkol.crc.careg.dto.MarkAppDTO;
import kkol.crc.careg.model.Car;
import kkol.crc.careg.model.FuelType;
import kkol.crc.careg.model.User;
import kkol.crc.careg.repository.CarRepository;
import kkol.crc.careg.repository.UserRepository;
import kkol.crc.careg.service.CarService;
import kkol.crc.careg.service.CarServiceImpl;
import kkol.crc.careg.service.ParentMarkService;
import kkol.crc.careg.service.ParentMarkServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    private CarService carService;
    private ParentMarkService parentMarkService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        carService = new CarServiceImpl(carRepository, userRepository);
        parentMarkService = new ParentMarkServiceImpl(carRepository);

    }

    @Test
    public void checkAddCarTest() {
        // given
        CarDTO carDTO = CarDTO.builder()
                .model("test_model")
                .mark("test_mark")
                .productionYear(1990)
                .enginePower(32f)
                .fuelType(FuelType.BIODIESEL)
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

    @Test
    public void checkExistingCarInParentApp() {
        // given
        MarkAppDTO markAppDTO = MarkAppDTO.builder()
                .markList(List.of("mark1", "mark2", "mark3"))
                .build();

        Car car1 = new Car(null, new User(), "mark1", "mod1", FuelType.BIODIESEL, 1990, 34f, 33f, false);
        Car car2 = new Car(null, new User(), "mark1", "mod2", FuelType.ELECTRIC, 332, 21f, 33f, false);
        Car car3 = new Car(null, new User(), "mark1", "mod3", FuelType.ETHANOL, 2010, 11f, 33f, false);

        given(carRepository.findCarsByMark("mark1")).willReturn(List.of(car1, car2, car3));
        given(carRepository.save(any())).will(returnsFirstArg());

        //when
        List<Car> changedCars = parentMarkService.updateCarByParentCarService("mark1", markAppDTO);

        //then
        then(changedCars).allMatch(Car::isInParentDatabase);

    }

    @Test
    public void updateExistingCar() {
        // given
        Car existingCar1 = new Car(22L, new User(), "mark1", "mod1", FuelType.BIODIESEL, 1990, 34f, 33f, false);

        given(carRepository.findCarsByMark("mark1")).willReturn(List.of(existingCar1));
        given(carRepository.save(any())).will(returnsFirstArg());

        CarDTO carDTO = CarDTO.builder()
                .model("modelEdited")
                .mark("mark1")
                .productionYear(1991)
                .enginePower(32f)
                .fuelType(FuelType.BIODIESEL)
                .weight(123f)
                .build();

        // when
        Car car = carService.updateCar(carDTO);

        // then
        then(car.getModel()).isEqualTo("modelEdited");
    }

    @Test
    public void registerNewCarInCaseOfUpdate(){
        // given
        Car newCar = new Car(11L, new User(), "mark5", "mod1", FuelType.BIODIESEL, 1990, 34f, 33f, false);

        given(carRepository.save(any())).will(returnsFirstArg());

        CarDTO carDTO = CarDTO.builder()
                .model("newModel")
                .mark("newMark")
                .productionYear(1991)
                .enginePower(32f)
                .fuelType(FuelType.BIODIESEL)
                .weight(123f)
                .build();

        given(carRepository.findCarsByMark(carDTO.getMark())).willReturn(List.of());

        // when
        Car car = carService.updateCar(carDTO);

        // then
        then(car.getModel()).isEqualTo("newModel");
        verify(carRepository).save(newCar);
       // verify(carRepository).findById(3L);

    }
}
