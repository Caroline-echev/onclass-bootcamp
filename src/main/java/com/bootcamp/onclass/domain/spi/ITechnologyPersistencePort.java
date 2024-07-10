package com.bootcamp.onclass.domain.spi;


import com.bootcamp.onclass.domain.model.Technology;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface ITechnologyPersistencePort {
    Technology addTechnology(Technology technology);
    Optional<Technology> getTechnologyByName(String name);
    Page<Technology> getAllTechnologies(Integer page, Integer size, boolean orderAsc);


}
