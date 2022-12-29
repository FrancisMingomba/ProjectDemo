package com.restdemo.restapidemo.error;

public class NoSuchAlgorithmException extends Exception {

    public NoSuchAlgorithmException() {
        super();
    }

    public NoSuchAlgorithmException(String message) {
        super(message);
    }

    public NoSuchAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchAlgorithmException(Throwable cause) {
        super(cause);
    }

    public NoSuchAlgorithmException(String message, Throwable cause, boolean enableSupression,
            boolean writableStackTrace) {
        super(message, cause, enableSupression, writableStackTrace);
    }

}
