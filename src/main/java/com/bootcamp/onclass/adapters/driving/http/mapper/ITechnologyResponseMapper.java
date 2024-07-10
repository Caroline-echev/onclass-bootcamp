package com.bootcamp.onclass.adapters.driving.http.mapper;

import com.bootcamp.onclass.adapters.driving.http.dto.response.technology.TechnologyResponse;
import com.bootcamp.onclass.domain.model.Technology;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyResponseMapper {
    TechnologyResponse toTechnologyResponse(Technology technology);


    TechnologyResponse toResponse(Technology technology);
}
