package com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.bootcamp.onclass.domain.model.Version;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IVersionEntityMapper {
    Version toModel(VersionEntity versionEntity);
    @InheritInverseConfiguration
    VersionEntity toEntity(Version version);
}
