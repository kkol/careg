package kkol.crc.careg.service;

import kkol.crc.careg.dto.MarkAppDTO;
import kkol.crc.careg.model.Car;
import kkol.crc.careg.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParentMarkServiceImpl implements ParentMarkService {

    private final CarRepository carRepository;

    @Override
    public List<Car> updateCarByParentCarService(String mark, MarkAppDTO markAppDTO) {
        List<Car> car = carRepository.findCarsByMark(mark);
        if (markAppDTO.markList.contains(mark)) {
            car.forEach(car1 -> {
                car1.setInParentDatabase(true);
                carRepository.save(car1);
                log.info("Update car {}", car1);
            });
        } else {
            log.info("Nothing to update");
        }
        return car;
    }
}
