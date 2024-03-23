package com.bootcamp.onclass.domain.spi;


import com.bootcamp.onclass.domain.model.Technology;

import java.util.Optional;


public interface ITechnologyPersistencePort {
    Technology addTechnology(Technology technology);
    Optional<Technology> getTechnologyByName(String name);

}
