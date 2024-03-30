package com.bootcamp.onclass.adapters.driving.http.dto.response.bootcamp;

import com.bootcamp.onclass.adapters.driving.http.dto.response.capacity.CapacitiesBootcampResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
public class BootcampResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final List<CapacitiesBootcampResponse> capacities;
}
