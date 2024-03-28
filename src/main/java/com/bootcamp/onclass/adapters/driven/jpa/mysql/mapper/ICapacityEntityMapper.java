package com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp.onclass.domain.model.Capacity;
import org.mapstruct.*;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ICapacityEntityMapper {

        Capacity toModel(CapacityEntity entity);
        @InheritInverseConfiguration
        CapacityEntity toEntity(Capacity capacity);
}
