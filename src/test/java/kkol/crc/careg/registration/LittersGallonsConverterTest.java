package kkol.crc.careg.registration;


import kkol.crc.careg.utils.LitersGallonConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LittersGallonsConverterTest {

    private final LitersGallonConverter littersGallonsConverter = new LitersGallonConverter();

    @Test
    public void convertLitterToGallonTest() {
        //given
        float litter = 1;
        float expected = 0.26417205f;

        // when
        float actual = littersGallonsConverter.litersToGallons(litter);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void convertGallonToLitter() {
        // given
        float gallon = 5;
        float expected = 18.92705892f;

        // when
        float actual = littersGallonsConverter.gallonsToLitters(gallon);

        //then
        Assertions.assertEquals(expected, actual);
    }
}
