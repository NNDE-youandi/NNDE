package com.ssafy.youandi.config.advice.exception;

public class InvalidRefreshTokenException extends RuntimeException{
    public InvalidRefreshTokenException() {
    }

    public InvalidRefreshTokenException(String message) {
        super(message);
    }

    public InvalidRefreshTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
