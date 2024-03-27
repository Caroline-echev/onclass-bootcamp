package com.bootcamp.onclass.adapters.driving.http.util;

public final class RequestConstants {
    private RequestConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_NAME_MAX_LENGTH_MESSAGE = "The name is longer than 50 characters";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE = "The description is longer than 90 characters";
}