package kkol.crc.careg.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import kkol.crc.careg.config.GlobalExceptionHandler;
import kkol.crc.careg.dto.CarDTO;
import kkol.crc.careg.model.Car;
import kkol.crc.careg.repository.CarRepository;
import kkol.crc.careg.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/car")
public class CarRegistrationController implements GlobalExceptionHandler {

    private final CarService carService;
    private final CarRepository carRepository;

    @PostMapping(path = "/register")
    public ResponseEntity<Car> addCar(@RequestBody @Valid CarDTO carDTO) {
        log.info("Received new car {}", carDTO.getMark() + " " + carDTO.getModel());
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.registerCar(carDTO));
    }

    @GetMapping()
    public ResponseEntity<Car> getCarById(@RequestParam("id") final Long id) {
        return ResponseEntity.ok(carRepository.findById(id).orElse(new Car()));
    }

    @GetMapping(path = "/all")
    public Iterable<Car> getCars() {
        return carRepository.findAll();
    }

    @DeleteMapping(path = "/delete/{carId}")
    public void deleteCarById(@PathVariable Long carId) {
        if (carRepository.findById(carId).isEmpty()) {
            throw new NoSuchElementException("Car with ID " + carId + " does not exist.");
        }
        carRepository.delete(carRepository.findById(carId).get());

    }

}
