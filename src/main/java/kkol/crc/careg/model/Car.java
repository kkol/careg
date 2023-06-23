package kkol.crc.careg.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(name = "unique_car",columnNames = {"mark", "model", "fuelType", "productionYear", "enginePower"})})
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String mark;
    private String model;
    private FuelType fuelType;
    private int productionYear;
    private float enginePower;
    private float weight;
    private boolean inParentDatabase;


}
