package com.bootcamp.onclass.domain.spi;

import com.bootcamp.onclass.domain.model.Bootcamp;

public interface IBootcampPersistencePort {
    Bootcamp addBootcamp(Bootcamp bootcamp);
}
