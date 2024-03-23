package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;


import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.exception.NotUniqueNameException;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {

    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;
    @Override
    public Technology addTechnology(Technology technology) {

        if (technologyRepository.findByName(technology.getName()).isPresent()) {
            throw new NotUniqueNameException("The name of the technology already exists");
        }
        technologyRepository.save(technologyEntityMapper.toEntity(technology));

        return technology;
    }


    @Override
    public Optional<Technology> getTechnologyByName(String name) {
        return technologyRepository.findByName(name).map(technologyEntityMapper::toModel);
    }
}
