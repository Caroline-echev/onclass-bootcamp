package com.bootcamp.onclass.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("utility class");
    }

     public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
     public static final String ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The element you want to create already exists";
    public static final String DUPLICATE_ITEMS_LIST_EXCEPTION_MESSAGE = "Duplicate items in the list";
    public  static final String VALIDATE_DATE_RANGE_EXCEPTION_MESSAGE = "The final date must be greater than the initial date";
    public static final String INVALID_DATE_FORMAT_EXCEPTION_MESSAGE = "Date format is incorrect, use yyyy-mm-dd";
}
