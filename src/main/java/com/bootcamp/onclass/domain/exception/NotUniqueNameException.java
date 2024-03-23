package com.bootcamp.onclass.domain.exception;

public class NotUniqueNameException extends RuntimeException{
    public NotUniqueNameException(String message) {
        super(message);
    }
}
