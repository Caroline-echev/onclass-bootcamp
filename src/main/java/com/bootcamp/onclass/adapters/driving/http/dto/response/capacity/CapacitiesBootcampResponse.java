package com.bootcamp.onclass.adapters.driving.http.dto.response.capacity;

import com.bootcamp.onclass.adapters.driving.http.dto.response.technology.TechnologiesCapacityBootcampResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CapacitiesBootcampResponse {

    private final Long id;
    private final String name;
    private final List<TechnologiesCapacityBootcampResponse> technologies;
}
