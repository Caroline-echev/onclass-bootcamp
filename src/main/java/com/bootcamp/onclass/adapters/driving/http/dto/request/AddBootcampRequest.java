package com.bootcamp.onclass.adapters.driving.http.dto.request;

import com.bootcamp.onclass.adapters.driving.http.util.RequestConstants;
import com.bootcamp.onclass.domain.model.Capacity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AddBootcampRequest {
    @NotBlank(message = RequestConstants.FIELD_NAME_NULL_MESSAGE)
    private final String name;

    @NotBlank(message = RequestConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    private final String description;

    @NotEmpty(message = RequestConstants.FIELD_LIST_ITEMS_EMPTY_MESSAGE)
    @Size(min = 1, max = 4, message = RequestConstants.FIELD_LIST_CAPACITIES_LENGTH_MESSAGE)
    private final List<Capacity> capacities;
}
