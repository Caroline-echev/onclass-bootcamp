package com.bootcamp.onclass.adapters.driving.http.mapper;

import com.bootcamp.onclass.adapters.driving.http.dto.response.bootcamp.BootcampResponse;
import com.bootcamp.onclass.domain.model.Bootcamp;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface IBootcampResponseMapper {

    BootcampResponse modelToFindResponse(Bootcamp bootcamp);
}
