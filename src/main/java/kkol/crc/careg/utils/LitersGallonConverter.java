package kkol.crc.careg.utils;

public class LitersGallonConverter {


    public Float litersToGallons(float litter) {
        return litter * 0.2641720524f;
    }

    public Float gallonsToLitters(float gallons) {
        return gallons / 0.2641720524f;
    }
}
