package com.ssafy.youandi.config.advice.exception;

public class DisagreePasswordException extends RuntimeException{
    public DisagreePasswordException() {
    }

    public DisagreePasswordException(String message) {
        super(message);
    }

    public DisagreePasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisagreePasswordException(Throwable cause) {
        super(cause);
    }

    public DisagreePasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
