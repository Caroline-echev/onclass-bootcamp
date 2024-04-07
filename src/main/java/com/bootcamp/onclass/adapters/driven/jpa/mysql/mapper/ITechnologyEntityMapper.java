package com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper;


import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp.onclass.domain.model.Technology;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ITechnologyEntityMapper {
    Technology toModel(TechnologyEntity technologyEntity);

    TechnologyEntity toEntity(Technology technology);
    List<Technology> toModelList(List<TechnologyEntity> technologyEntities);

}