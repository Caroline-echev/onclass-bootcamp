package com.bootcamp.onclass.adapters.driving.http.dto.request;



import com.bootcamp.onclass.adapters.driving.http.util.RequestConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class AddTechnologyRequest {
    @NotBlank(message = RequestConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(max = 50, message = RequestConstants.FIELD_NAME_MAX_LENGTH_MESSAGE)
    private final  String name;
    @NotBlank(message = RequestConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    @Size(max = 90, message = RequestConstants.FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE)
    private final String description;

}
