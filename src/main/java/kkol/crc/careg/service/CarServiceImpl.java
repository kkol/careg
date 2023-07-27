package kkol.crc.careg.service;

import kkol.crc.careg.dto.CarDTO;
import kkol.crc.careg.model.Car;
import kkol.crc.careg.model.FuelType;
import kkol.crc.careg.model.User;
import kkol.crc.careg.repository.CarRepository;
import kkol.crc.careg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Override
    public Car registerCar(CarDTO carDTO) {
        //build car object that will be saved in db
        User tempUser = new User(null, "default_user");
        User user = userRepository.findByAlias(tempUser.getAlias()).orElseGet(() -> {
            log.info("Creating new user with name {}", tempUser.getAlias());
            return userRepository.save(tempUser);
        });

        Car car = new Car(null, user, carDTO.getMark(), carDTO.getModel(), carDTO.getFuelType(), carDTO.getProductionYear(), carDTO.getEnginePower(), carDTO.getWeight(), false);

        return carRepository.save(car);
    }

}
