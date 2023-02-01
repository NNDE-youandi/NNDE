package com.ssafy.youandi.advice.exception;

public class CommunicationException extends RuntimeException{
    public CommunicationException() {
    }

    public CommunicationException(String message) {
        super(message);
    }

    public CommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
