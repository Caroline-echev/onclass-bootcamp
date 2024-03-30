package com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp.onclass.domain.model.Bootcamp;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IBootcampEntityMapper {
    Bootcamp toModel(BootcampEntity bootcampEntity);
    @InheritInverseConfiguration
    BootcampEntity toEntity(Bootcamp bootcamp);
    List<Bootcamp> toModelList(List<BootcampEntity> bootcampEntities);
}
