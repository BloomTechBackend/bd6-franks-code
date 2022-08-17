package com.amazon.ata.immutabilityandfinal.classroom.primephoto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class representing a PrimePhoto - contains dimensions, and a list of Pixels that make up the image.
 */
// Make the immutable so it works properly in a concurrent processing
//    1. Make the class final
//    2. Make the instance variables final and change code accordingly
//    3. Use defensive copy and defensive return for references received or returned
//    4. Ensure there are no setters for instance variables

public final class PrimePhoto {
    private final List<Pixel> pixels;
    private final int height;
    private final int width;
    // used when saving to a buffered image
    private final int type;

    // This ctor receives a reference to a List
    //      we need to use defensive copy to store the data
    public PrimePhoto(List<Pixel> pixelList, int height, int width, int type) {
        if (pixelList.size() != (height * width)) {
            throw new IllegalArgumentException("Not enough pixels for the dimensions of the image.");
        }
   //   this.pixels = pixelList;  // replaced by defensive copy
        this.pixels = new ArrayList<>(pixelList);
        this.height = height;
        this.width  = width;
        this.type   = type;
    }

    // We are returning a List which is a reference object
    //      we need to use defensive return when returning the data
    public List<Pixel> getPixels() {
    //  return pixels;  // replaced by defensive return
        return new ArrayList<>(pixels);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pixels, height, width, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        PrimePhoto photo = (PrimePhoto) obj;

        return photo.height == this.height && photo.width == this.width &&
            photo.type == photo.type && Objects.equals(photo.pixels, this.pixels);
    }

}
