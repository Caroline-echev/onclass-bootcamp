package com.bootcamp.onclass.adapters.driving.http.dto.request;

import com.bootcamp.onclass.adapters.driving.http.util.RequestConstants;
import com.bootcamp.onclass.domain.model.Bootcamp;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class AddVersionRequest {



    @NotBlank(message = RequestConstants.FIELD_DATE_NULL_MESSAGE)
    private final String initialDate;


    @NotBlank(message = RequestConstants.FIELD_DATE_NULL_MESSAGE)
    private final  String finalDate;

    @Min(value =  1, message = RequestConstants.FIELD_MAX_CAPACITY_MIN_MESSAGE)
    @NotNull(message = RequestConstants.FIELD_MAX_CAPACITY_NULL_MESSAGE)
    private final int maxCapacity;

    @NotNull(message = RequestConstants.FIELD_BOOTCAMP_EMPTY_MESSAGE)
    private final Long bootcampId;

}
