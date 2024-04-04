package com.bootcamp.onclass.adapters.driving.http.util;

public final class RequestConstants {
    private RequestConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIELD_NAME_NULL_MESSAGE = "Field 'name' cannot be null";
    public static final String FIELD_NAME_MAX_LENGTH_MESSAGE = "The name is longer than 50 characters";
    public static final String FIELD_DESCRIPTION_NULL_MESSAGE = "Field 'description' cannot be null";
    public static final String FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE = "The description is longer than 90 characters";
    public static final String FIELD_LIST_TECHNOLOGIES_LENGTH_MESSAGE = "The list should have from 3 to 20 technologies ";
    public static final String FIELD_LIST_ITEMS_EMPTY_MESSAGE = "The list of items is empty ";
    public static final String FIELD_LIST_CAPACITIES_LENGTH_MESSAGE = "The list should have from 1 to 4 capacities ";
    public static final String FIELD_DATE_NULL_MESSAGE = "Date cannot be null";
    public static final String FIELD_MAX_CAPACITY_NULL_MESSAGE = "Field 'maxCapacity' cannot be null";
    public static final String FIELD_BOOTCAMP_EMPTY_MESSAGE = "Field 'bootcamp' cannot be null ";
    public static final String FIELD_MAX_CAPACITY_MIN_MESSAGE = "Field 'maxCapacity' must be greater than 0";
    public static final String FIELD_DATE_FORMAT_MESSAGE = "dd/MM/yyyy";
    public static final String FIELD_DATE_ALREADY_PASSED_MESSAGE = "The date has already passed ";

}