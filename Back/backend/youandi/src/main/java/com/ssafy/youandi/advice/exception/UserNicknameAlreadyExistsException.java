package com.ssafy.youandi.advice.exception;

public class UserNicknameAlreadyExistsException extends RuntimeException {
    public UserNicknameAlreadyExistsException(){};
    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
    public UserEmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
