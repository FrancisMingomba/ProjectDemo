package com.restdemo.restapidemo.error;

public class AutheticationException extends Exception {

    public AutheticationException() {
        super();
    }

    public AutheticationException(String message) {
        super(message);
    }

    public AutheticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutheticationException(Throwable cause) {
        super(cause);
    }

    public AutheticationException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
