package com.bootcamp.onclass.adapters.driving.http.mapper;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddVersionRequest;
import com.bootcamp.onclass.domain.model.Version;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IVersionRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "bootcampId", target = "bootcamp.id")
    Version addRequestToVersion(AddVersionRequest addVersionRequest);
}
