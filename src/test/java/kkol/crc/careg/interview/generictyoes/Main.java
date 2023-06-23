package kkol.crc.careg.interview.generictyoes;

public class Main {

    public static void main(String[] args) {
        BigGarage<Bike> bikeInGarage = new BigGarage<>(new Bike(2));
        BigGarage<Motorbike> motorbikeInGarage = new BigGarage<>(new Motorbike(2, "2.6", "DIESEL"));

        Motorbike motorbike = motorbikeInGarage.getVehicle();
        Bike bike = bikeInGarage.getVehicle();


        motorbikeInGarage.moveFromGarage();

        System.out.println(bike.getEngine());
        System.out.println(motorbike.getFuel());

    }
}
