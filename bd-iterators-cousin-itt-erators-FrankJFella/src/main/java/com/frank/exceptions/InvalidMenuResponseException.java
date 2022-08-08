package com.frank.exceptions;

public class InvalidMenuResponseException extends RuntimeException {
        public InvalidMenuResponseException() {
                super();
        }
        public InvalidMenuResponseException(String message) {
                super(message);
        }
}
