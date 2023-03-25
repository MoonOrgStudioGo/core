package com.example.base.exceptions

class BaseException extends RuntimeException
        implements Serializable {

    private static final long serialVersionUID = 1L

    BaseException(String message) {
        super(message)
    }

    BaseException(String message, Throwable cause) {
        super(message, cause)
    }

    BaseException(Throwable cause) {
        super(cause)
    }

    BaseException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace)
    }
}