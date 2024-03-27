package com.bootcamp.onclass.adapters.driving.http.mapper;


import com.bootcamp.onclass.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp.onclass.domain.model.Capacity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICapacityRequestMapper {
    @Mapping(target = "id", ignore = true )
    Capacity addRequestToCapacity(AddCapacityRequest addCapacityRequest);
}
