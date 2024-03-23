package com.bootcamp.onclass.domain.api;

import com.bootcamp.onclass.domain.model.Technology;

import java.util.Optional;


public interface ITechnologyServicePort {

    Technology addTechnology(Technology technology);
    Optional<Technology> getTechnologyByName(String name);
}
