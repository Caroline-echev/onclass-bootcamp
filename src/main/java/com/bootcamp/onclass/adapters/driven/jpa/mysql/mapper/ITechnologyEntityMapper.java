package com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper;


import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp.onclass.domain.model.Technology;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITechnologyEntityMapper {
    Technology toModel(TechnologyEntity technologyEntity);
    TechnologyEntity toEntity(Technology technology);


}