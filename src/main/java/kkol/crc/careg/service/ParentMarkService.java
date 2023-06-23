package kkol.crc.careg.service;

import kkol.crc.careg.dto.MarkAppDTO;
import kkol.crc.careg.model.Car;

import java.util.List;

public interface ParentMarkService {
    List<Car> updateCarByParentCarService(String mark, MarkAppDTO markAppDTO);
}
