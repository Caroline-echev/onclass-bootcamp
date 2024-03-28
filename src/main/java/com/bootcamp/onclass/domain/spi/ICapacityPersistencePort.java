package com.bootcamp.onclass.domain.spi;


import com.bootcamp.onclass.domain.model.Capacity;

public interface ICapacityPersistencePort {
    Capacity addCapacity(Capacity capacity);
}
