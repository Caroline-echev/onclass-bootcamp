package com.bootcamp.onclass.adapters.driving.http.dto.request;


import com.bootcamp.onclass.adapters.driving.http.util.RequestConstants;
import com.bootcamp.onclass.domain.model.Technology;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.coyote.Request;

import java.util.List;


@AllArgsConstructor
@Getter
public class AddCapacityRequest {
    @NotBlank(message = RequestConstants.FIELD_NAME_NULL_MESSAGE)
    private final String name;
    @NotBlank(message = RequestConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    private final String description;
    @NotEmpty(message = RequestConstants.FIELD_LIST_TECHNOLOGIES_EMPTY_MESSAGE)
    @Size(min = 3, max = 20, message = RequestConstants.FIELD_LIST_TECHNOLOGIES_LENGTH_MESSAGE)
    private final List<Technology> technologies;
}
