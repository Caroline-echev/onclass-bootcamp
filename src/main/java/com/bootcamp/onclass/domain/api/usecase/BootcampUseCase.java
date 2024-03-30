package com.bootcamp.onclass.domain.api.usecase;

import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.api.IBootcampServicePort;
import com.bootcamp.onclass.domain.exception.DuplicateItemsListException;
import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.spi.IBootcampPersistencePort;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BootcampUseCase implements IBootcampServicePort {

    private final IBootcampPersistencePort bootcampPersistencePort;

    public BootcampUseCase(IBootcampPersistencePort bootcampPersistencePort) {
        this.bootcampPersistencePort = bootcampPersistencePort;
    }

    @Override
    public Bootcamp addBootcamp(Bootcamp bootcamp) {
        if(!validateDuplicateCapacities(bootcamp.getCapacities())){
            throw new DuplicateItemsListException(Constants.DUPLICATE_ITEMS_LIST_EXCEPTION_MESSAGE);
        }
        return bootcampPersistencePort.addBootcamp(bootcamp);
    }
    @Override
    public List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean orderAsc , boolean orderName) {
        return bootcampPersistencePort.getAllBootcamps(page, size, orderAsc, orderName);

    }
    private boolean validateDuplicateCapacities(List<Capacity> capacities) {
        Set<Long> capacityId = new HashSet<>();
        for (Capacity capacity : capacities) {
            if ( capacityId.contains( capacity.getId() ) ) {
                return false;
            }
            capacityId.add( capacity.getId() );
        }
        return true;

    }
}
