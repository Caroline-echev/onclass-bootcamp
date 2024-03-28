package com.bootcamp.onclass.adapters.driving.http.util;

public final class RequestConstants {
    private RequestConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_NAME_MAX_LENGTH_MESSAGE = "The name is longer than 50 characters";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE = "The description is longer than 90 characters";
    public static final String FIELD_LIST_TECHNOLOGIES_EMPTY_MESSAGE = "The list of technologies is empty ";
    public static final String FIELD_LIST_TECHNOLOGIES_LENGTH_MESSAGE = "The list should have from 3 to 20 technologies ";

}