package kkol.crc.careg.interview.generictyoes;


public class Bike extends Vehicle {

    public Bike(int wheel){
        super(wheel, null, null);
    }

    @Override
    public String toString(){
        return "This is bike";
    }
}
