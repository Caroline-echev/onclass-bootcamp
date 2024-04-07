package com.bootcamp.onclass.adapters.driving.http.dto.request;

import com.bootcamp.onclass.adapters.driving.http.util.RequestConstants;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class AddVersionRequest {



    @NotNull(message = RequestConstants.FIELD_DATE_NULL_MESSAGE)
    @FutureOrPresent(message = RequestConstants.FIELD_DATE_ALREADY_PASSED_MESSAGE)
    private final LocalDate initialDate;


    @NotNull(message = RequestConstants.FIELD_DATE_NULL_MESSAGE)
    @FutureOrPresent(message = RequestConstants.FIELD_DATE_ALREADY_PASSED_MESSAGE)
    private final LocalDate finalDate;

    @Min(value =  1, message = RequestConstants.FIELD_MAX_CAPACITY_MIN_MESSAGE)
    @NotNull(message = RequestConstants.FIELD_MAX_CAPACITY_NULL_MESSAGE)
    private final int maxCapacity;

    @NotNull(message = RequestConstants.FIELD_BOOTCAMP_EMPTY_MESSAGE)
    private final Long bootcampId;

}
