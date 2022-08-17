package com.amazon.ata.immutabilityandfinal.classroom.introspection;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.RGB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RGBIntrospectionTest {

    private RGB rgb;
    private RGB rgbCopy;

    @BeforeEach
    public void setup() {
        rgb = new RGB(0, 1, 2, 255);
        rgbCopy = new RGB(0, 1, 2, 255);
    }

    @Test
    public void rgb_toGreyScale_preventsImmutability() {
        // WHEN
        rgb.toGreyScale();;

        assertEquals(rgbCopy, rgb, "Method: toGreyScale() mutated internal state.");
    }

    @Test
    public void rgb_invert_preventsImmutability() {
        // WHEN
        rgb.invert();;

        assertEquals(rgbCopy, rgb, "Method: invert() mutated internal state.");
    }

    @Test
    public void rgb_toSepia_preventsImmutability() {
        // WHEN
        rgb.toSepia();;

        assertEquals(rgbCopy, rgb, "Method: toGreyScale() mutated internal state.");
    }

    @Test
    public void rgb_class_isNotExtensible() {
        //GIVEN
        Class c = RGB.class;

        //WHEN
        boolean isFinal = Modifier.isFinal(c.getModifiers());

        //THEN
        Assertions.assertTrue(isFinal, "Immutable classes should not be extendable.");
    }

    @Test
    public void rgb_fields_areFinal() {
        //GIVEN
        Class c  = RGB.class;

        //WHEN & THEN
        for (Field f : c.getDeclaredFields()) {
            Assertions.assertTrue(Modifier.isFinal(f.getModifiers()),
                "At least one field was not declared final.");
        }
    }
}
