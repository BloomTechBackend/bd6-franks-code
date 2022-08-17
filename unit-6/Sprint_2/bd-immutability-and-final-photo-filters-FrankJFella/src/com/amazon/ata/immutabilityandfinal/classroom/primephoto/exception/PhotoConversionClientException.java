package com.amazon.ata.immutabilityandfinal.classroom.primephoto.exception;

/**
 * Exception thrown by the Prime Photo Conversion Service when an error occurs due to a client error.
 */
public class PhotoConversionClientException extends RuntimeException {

    private static final long serialVersionUID = -3380299998269274387L;

    public PhotoConversionClientException(String message) {
        super(message);
    }

    public PhotoConversionClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
