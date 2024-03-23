package com.bootcamp.onclass.adapters.driving.http.mapper;

import com.bootcamp.onclass.adapters.driving.http.dto.response.TechnologyResponse;
import com.bootcamp.onclass.domain.model.Technology;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyResponseMapper {
    TechnologyResponse toTechnologyResponse(Technology technology);

}
