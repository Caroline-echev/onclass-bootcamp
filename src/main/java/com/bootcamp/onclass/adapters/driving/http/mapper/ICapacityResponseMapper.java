package com.bootcamp.onclass.adapters.driving.http.mapper;


import com.bootcamp.onclass.adapters.driving.http.dto.response.CapacityResponse;
import com.bootcamp.onclass.domain.model.Capacity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ICapacityResponseMapper {
    List<CapacityResponse> toCapacityResponseList(List<Capacity> capacities);
}
