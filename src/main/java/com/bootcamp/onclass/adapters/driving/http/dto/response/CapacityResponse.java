package com.bootcamp.onclass.adapters.driving.http.dto.response;
import com.bootcamp.onclass.domain.model.Technology;
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
