package com.app.dddlite.exception;

public class CustomException extends Exception {

    public CustomException(ErrorMessage message) {
        super(message.getMessage());
    }
}
