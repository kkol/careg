package kkol.crc.careg.interview.generictyoes;

public class Motorbike extends Vehicle {

    public Motorbike(int wheel, String engine, String fuelType){
        super(wheel, engine, fuelType);
    }

    @Override
    public String toString(){
        return "This is motorbike";
    }
}
