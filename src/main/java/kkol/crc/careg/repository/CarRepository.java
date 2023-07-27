package kkol.crc.careg.repository;

import jakarta.validation.constraints.NotNull;
import kkol.crc.careg.dto.CarDTO;
import kkol.crc.careg.model.Car;
import kkol.crc.careg.model.FuelType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;


public interface CarRepository extends CrudRepository<Car, Long> {
    @Query("""
            select (count(c) > 0) from Car c
            where c.mark = ?1 and c.model = ?2 and c.fuelType = ?3 and c.productionYear = ?4 and c.enginePower = ?5""")
    boolean ifCarAlreadyExists(@NonNull String mark, @NonNull String model, @NonNull FuelType fuelType, @NonNull int productionYear, @NonNull float enginePower);

}
