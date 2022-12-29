package com.restdemo.restapidemo.error;

public class EmptyFileException extends Exception {
    public EmptyFileException() {
        super();
    }

    public EmptyFileException(String message) {
        super(message);
    }

    public EmptyFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyFileException(Throwable cause) {
        super(cause);
    }

    public EmptyFileException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
