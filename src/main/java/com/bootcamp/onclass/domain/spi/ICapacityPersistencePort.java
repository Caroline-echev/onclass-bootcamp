package com.bootcamp.onclass.domain.spi;


import com.bootcamp.onclass.domain.model.Capacity;

import java.util.List;

public interface ICapacityPersistencePort {
    Capacity addCapacity(Capacity capacity);
    List<Capacity> getAllCapacities(Integer page, Integer size, boolean orderAsc, boolean orderName);

}
