package com.bootcamp.onclass.adapters.driving.http.mapper;

import com.bootcamp.onclass.adapters.driving.http.dto.response.version.VersionResponse;
import com.bootcamp.onclass.domain.model.Version;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IVersionResponseMapper {
    VersionResponse modelToFindResponse(Version version);
}
