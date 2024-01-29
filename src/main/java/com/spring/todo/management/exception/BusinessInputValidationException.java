package com.spring.todo.management.exception;

public class BusinessInputValidationException extends RuntimeException {

    public BusinessInputValidationException(String message){
        super(message);
    }

    public BusinessInputValidationException(String message, Exception ex) {
        super(message, ex);
    }
}
