package com.bootcamp.onclass.domain.api.usecase;


import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.api.ICapacityServicePort;
import com.bootcamp.onclass.domain.exception.DuplicateItemsListException;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.spi.ICapacityPersistencePort;

import java.util.*;

public class CapacityUseCase implements ICapacityServicePort {
    private final ICapacityPersistencePort capacityPersistencePort;
    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort) {
        this.capacityPersistencePort = capacityPersistencePort;
    }
    @Override
    public Capacity addCapacity(Capacity capacity) {
        if(!validateDuplicateTechnologies(capacity.getTechnologies())){
            throw new DuplicateItemsListException(Constants.DUPLICATE_ITEMS_LIST_EXCEPTION_MESSAGE);
        }
        return capacityPersistencePort.addCapacity(capacity);
    }
    @Override
    public List<Capacity> getAllCapacities(Integer page, Integer size, boolean orderAsc , boolean orderName) {
        return capacityPersistencePort.getAllCapacities(page, size, orderAsc, orderName);

    }
    private boolean validateDuplicateTechnologies(List<Technology> technologies) {
        Set<Long> technologyId = new HashSet<>();
        for (Technology technology : technologies) {
            if ( technologyId.contains( technology.getId() ) ) {
                return false;
            }
            technologyId.add( technology.getId() );
        }
        return true;
    }
}
