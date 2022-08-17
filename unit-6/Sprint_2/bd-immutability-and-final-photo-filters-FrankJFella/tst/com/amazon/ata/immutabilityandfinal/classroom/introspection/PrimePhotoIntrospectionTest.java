package com.amazon.ata.immutabilityandfinal.classroom.introspection;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.Pixel;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.PrimePhoto;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.RGB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimePhotoIntrospectionTest {

    private RGB rgb;
    private Pixel pixel;

    @BeforeEach
    public void setup() {
        rgb = new RGB(255, 255, 255, 255);
        pixel = new Pixel(0, 0, rgb);
    }

    @Test
    public void primePhoto_constructor_preventsMutability() {
        // GIVEN
        List<Pixel> pixels = new ArrayList<>();
        pixels.add(pixel);
        PrimePhoto photo = new PrimePhoto(pixels, 1, 1, 1);

        // WHEN
        pixels.add(pixel);

        // THEN
        assertEquals(1, photo.getPixels().size(),
            "PrimePhoto constructor does not prevent mutation of internal state.");
    }

    @Test
    public void primePhoto_getPixels_preventsMutability() {
        // GIVEN
        List<Pixel> pixels = new ArrayList<>();
        pixels.add(pixel);
        PrimePhoto photo = new PrimePhoto(pixels, 1, 1, 1);

        // WHEN
        List<Pixel> pixelsFromGetter =  photo.getPixels();
        pixelsFromGetter.add(pixel);

        // THEN
        assertEquals(1, photo.getPixels().size(),
            "Pixel getter does not prevent mutation of internal state.");
    }

    @Test
    public void primePhoto_class_isNotExtensible() {
        //GIVEN
        Class c = PrimePhoto.class;

        //WHEN
        boolean isFinal = Modifier.isFinal(c.getModifiers());

        //THEN
        assertTrue(isFinal, "Immutable classes should not be extendable.");
    }

    @Test
    public void primePhoto_fields_areFinal() {
        //GIVEN
        Class c  = PrimePhoto.class;

        //WHEN & THEN
        for (Field f : c.getDeclaredFields()) {
            assertTrue(Modifier.isFinal(f.getModifiers()),
                "At least one field was not declared final.");
        }
    }
}
