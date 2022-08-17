package com.amazon.ata.immutabilityandfinal.classroom.primephoto.converter;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.PrimePhoto;

/**
 * Strategy interface for converting images.
 */
public interface PrimePhotoConverter {

    String convert(PrimePhoto image, String imageName);
}
