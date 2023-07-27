package kkol.crc.careg.service;

import kkol.crc.careg.dto.CarDTO;
import kkol.crc.careg.model.Car;

public interface CarService {
    Car registerCar(CarDTO carDTO);
}
