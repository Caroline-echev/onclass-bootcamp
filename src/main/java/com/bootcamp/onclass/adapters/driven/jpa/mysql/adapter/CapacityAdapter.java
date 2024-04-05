package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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

    @Override
    public List<Capacity> getAllCapacities(Integer page, Integer size, boolean orderAsc, boolean orderName) {
        Sort sort;
        Pageable pageable;
        List<CapacityEntity> capacities = null; 
        if (orderName) {
            sort = orderAsc ? Sort.by("name").ascending() : Sort.by("name").descending();
            pageable = PageRequest.of(page, size, sort);
            capacities = capacityRepository.findAll(pageable).getContent();
        } else {
             pageable = PageRequest.of(page, size);
            capacities = orderAsc ? capacityRepository.findAllOrderedByTechnologySizeAsc(pageable).getContent() :
                    capacityRepository.findAllOrderedByTechnologySizeDesc(pageable).getContent();
        }

        if (capacities.isEmpty()) {
            throw new NoDataFoundException(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        }

        return capacityEntityMapper.toModelList(capacities);
    }



}
