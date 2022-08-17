package com.amazon.ata.immutabilityandfinal.classroom.primephoto.model;

import java.util.Objects;

/**
 * Represents a single point in an image. Each pixel has a location in the image, and an associated RGB color.
 */
// Make the immutable so it works properly in a concurrent processing
//    1. Make the class final
//    2. Make the instance variables final and change code accordingly
//    3. Use defensive copy and defensive return for references received or returned
//    4. Ensure there are no setters for instance variables

public final class Pixel {
    private final int x;
    private final int y;
    private final RGB rgb;

    // We receive a reference to RGB object - defensive copy it
    public Pixel(int x, int y, RGB rgb) {
        this.x = x;
        this.y = y;
     // this.rgb = rgb;  // replaced by defensive copy
        // since the RGB class has not implemented a clone() method
        //       we will create a new RGB object from the parameter using the ctor
        this.rgb = new RGB(rgb.getRed(), rgb.getGreen(),rgb.getBlue(), rgb.getTransparency());
    }

    // We are returning a reference - return a copy of the reference - defensive return
    public RGB getRGB() {
        // return rgb; // Replaced by defensive return
        // since the RGB class has not implemented a clone() method
        //       we will create a new RGB object from the parameter using the ctor
        return new RGB(rgb.getRed(), rgb.getGreen(),rgb.getBlue(), rgb.getTransparency());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, rgb);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Pixel pixel = (Pixel) obj;

        return pixel.x == this.x && pixel.y == this.y &&
           Objects.equals(pixel.rgb, this.rgb);
    }
}
