package com.bootcamp.onclass.domain.api;

import com.bootcamp.onclass.domain.model.Bootcamp;

import java.util.List;

public interface IBootcampServicePort {
    Bootcamp addBootcamp(Bootcamp bootcamp);
    List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean orderAsc, boolean orderName);

}
