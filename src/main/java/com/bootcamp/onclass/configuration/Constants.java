package com.bootcamp.onclass.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

     public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
     public static final String ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The element you want to create already exists";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field %s can not be empty";
    public static final String DUPLICATE_ITEMS_LIST_EXCEPTION_MESSAGE = "Duplicate items in the list";

}
