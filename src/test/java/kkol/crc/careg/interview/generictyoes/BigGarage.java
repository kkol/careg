package kkol.crc.careg.interview.generictyoes;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BigGarage<T extends Vehicle> {

    public T vehicle;

    public void moveFromGarage() {
        System.out.println(vehicle.toString());
    }


}
