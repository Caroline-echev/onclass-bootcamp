package com.bootcamp.onclass.domain.exception;

public class ElementAlreadyExistsException extends RuntimeException{
    public ElementAlreadyExistsException(String message) {
        super(message);
    }
}
