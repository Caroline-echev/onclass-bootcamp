package com.bootcamp.onclass.domain.spi;

import com.bootcamp.onclass.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampPersistencePort {
    Bootcamp addBootcamp(Bootcamp bootcamp);
    List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean orderAsc, boolean orderName);

}
