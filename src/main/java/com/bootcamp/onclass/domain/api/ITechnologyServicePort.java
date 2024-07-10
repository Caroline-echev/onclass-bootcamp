package com.bootcamp.onclass.domain.api;

import com.bootcamp.onclass.domain.model.Technology;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface ITechnologyServicePort {

    Technology addTechnology(Technology technology);
    Optional<Technology> getTechnologyByName(String name);
    Page<Technology> getAllTechnologies(Integer page, Integer size, boolean orderAsc);
}
