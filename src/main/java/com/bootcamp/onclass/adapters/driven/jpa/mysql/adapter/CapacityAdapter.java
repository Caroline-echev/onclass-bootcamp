package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.exception.DuplicateTechnologiesListException;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class CapacityAdapter implements ICapacityPersistencePort {
    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;


    @Override
        public void addCapacity(Capacity capacity) {
            List<Technology> technologies = capacity.getTechnologies();
            Set<Long> technologyIds = new HashSet<>();
            for (Technology technology : technologies) {
                Long techId = technology.getId();
                if (!technologyIds.add(techId)) {
                    throw new DuplicateTechnologiesListException(Constants.DUPLICATE_TECHNOLOGIES_LIST_EXCEPTIN_MESSAGE);
                }
            }
            capacityRepository.save(capacityEntityMapper.toEntity(capacity));
    }
}
