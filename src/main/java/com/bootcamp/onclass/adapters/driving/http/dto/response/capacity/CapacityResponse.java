package com.bootcamp.onclass.adapters.driving.http.dto.response.capacity;

import com.bootcamp.onclass.adapters.driving.http.dto.response.technology.TechnologiesCapacityResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CapacityResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final List<TechnologiesCapacityResponse> technologies;
}
