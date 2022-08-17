package com.amazon.ata.immutabilityandfinal.classroom.primephoto.converter.concurrent;

import com.amazon.ata.immutabilityandfinal.classroom.primephoto.converter.PrimePhotoConverter;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.exception.PhotoConversionServiceException;
import com.amazon.ata.immutabilityandfinal.classroom.primephoto.model.PrimePhoto;

/**
 * A ConcurrentConverter executes a wrapped PrimePhotoConverter's convert() method asynchronously. When the run method
 * has completed, it will have populated a String field that provides the location of the converted image.
 */
public class ConcurrentConverter implements Runnable {

    private final PrimePhotoConverter converter;
    private final PrimePhoto inputImage;
    private final String fileName;
    private String convertedImageLocation;

    public ConcurrentConverter(final PrimePhotoConverter converter, final PrimePhoto inputImage, final String fileName) {
        this.converter = converter;
        this.inputImage = inputImage;
        this.fileName = fileName;
    }

    public String getConvertedImageLocation() {
        if (convertedImageLocation == null) {
            throw new IllegalStateException("Conversion computation not complete yet, or did not succeed.");
        }
        return convertedImageLocation;
    }

    @Override
    public void run() {
        // Calls one of the strategies that implements PrimePhotoConverter
        convertedImageLocation = converter.convert(inputImage, fileName);
    }
}
