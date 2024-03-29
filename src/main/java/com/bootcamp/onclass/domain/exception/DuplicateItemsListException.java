package com.bootcamp.onclass.domain.exception;

public class DuplicateItemsListException extends RuntimeException{
    public DuplicateItemsListException(String message) {
        super(message);
    }
}
