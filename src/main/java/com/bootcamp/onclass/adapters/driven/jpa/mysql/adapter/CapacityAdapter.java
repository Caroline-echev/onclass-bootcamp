package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {

    private final ICapacityRepository capacityRepository;

    private final ICapacityEntityMapper capacityEntityMapper;

    @Override
    public Capacity addCapacity(Capacity capacity) {

        if (capacityRepository.findByName(capacity.getName()).isPresent()) {
            throw new ElementAlreadyExistsException(Constants.ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }
        capacityRepository.save(capacityEntityMapper.toEntity(capacity));

        return capacity;

    }
}
