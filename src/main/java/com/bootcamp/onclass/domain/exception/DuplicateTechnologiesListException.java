package com.bootcamp.onclass.domain.exception;

public class DuplicateTechnologiesListException extends RuntimeException{
    public DuplicateTechnologiesListException(String message) {
        super(message);
    }
}
