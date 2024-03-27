package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;


import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {

    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;
    @Override
    public Technology addTechnology(Technology technology) {

        if (technologyRepository.findByName(technology.getName()).isPresent()) {
            throw new ElementAlreadyExistsException(Constants.ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
        technologyRepository.save(technologyEntityMapper.toEntity(technology));

        return technology;
    }

    @Override
    public Optional<Technology> getTechnologyByName(String name) {
        return technologyRepository.findByName(name).map(technologyEntityMapper::toModel);
    }
    @Override
    public List<Technology> getAllTechnologies(Integer page, Integer size, boolean orderAsc) {
        Sort sort = orderAsc ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        List<TechnologyEntity> technologies = technologyRepository.findAll(pageable).getContent();


        if (technologies.isEmpty()) {
            throw new NoDataFoundException("No data was found in the database");
        }

        return technologyEntityMapper.toModelList(technologies);
    }
}
