package com.bootcamp.onclass.adapters.driving.http.dto.request;



import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class AddTechnologyRequest {

    @Size(max = 50, message = "The name is longer than 50 characters")
    private final  String name;

    @Size(max = 90, message = "The description is longer than 90 characters")
    private final String description;

}
