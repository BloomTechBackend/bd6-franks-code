package com.amazon.ata.immutabilityandfinal.classroom.introspection;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.Pixel;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.RGB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixelIntrospectionTest {
    private RGB rgb;
    private RGB rgbCopy;
    private Pixel pixel;
    private Pixel pixelCopy;

    @BeforeEach
    public void setup() {
        rgb = new RGB(0, 255, 255, 255);
        rgbCopy = new RGB(0, 255, 255, 255);
        pixel =  new Pixel(0, 0, rgb);
        pixelCopy = new Pixel(0, 0, rgbCopy);
    }

    @Test
    public void pixel_constructor_preventsMutability() {
        // WHEN
        rgb.toGreyScale();

        // THEN
        assertEquals(pixel, pixelCopy, "Pixel constructor does not prevent mutation of internal state.");
    }

    @Test
    public void pixel_getRGB_preventsMutability() {
        // WHEN
        RGB rgbFromGetter = pixel.getRGB();
        rgbFromGetter.toGreyScale();

        // THEN
        assertEquals(pixel, pixelCopy, "RGB getter does not prevent mutation of internal state.");
    }

    @Test
    public void pixel_class_isNotExtensible() {
        //GIVEN
        Class c = Pixel.class;

        //WHEN
        boolean isFinal = Modifier.isFinal(c.getModifiers());

        //THEN
        Assertions.assertTrue(isFinal, "Immutable classes should not be extendable.");
    }

    @Test
    public void pixel_fields_areFinal() {
        //GIVEN
        Class c  = Pixel.class;

        //WHEN & THEN
        for (Field f : c.getDeclaredFields()) {
            Assertions.assertTrue(Modifier.isFinal(f.getModifiers()),
                "At least one field was not declared final.");
        }
    }
}
