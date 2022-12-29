package com.restdemo.restapidemo.error;

public class UnknownApplicationTypeException extends Exception {
    public UnknownApplicationTypeException() {
        super();
    }

    public UnknownApplicationTypeException(String message) {
        super(message);

    }

    public UnknownApplicationTypeException(String message, Throwable cause) {
        super(message, cause);

    }

    public UnknownApplicationTypeException(Throwable cause) {
        super(cause);

    }

    public UnknownApplicationTypeException(String message, Throwable cause, boolean enableSupression,
            boolean writableStackTrace) {
        super(message, cause, enableSupression, writableStackTrace);

    }

}
