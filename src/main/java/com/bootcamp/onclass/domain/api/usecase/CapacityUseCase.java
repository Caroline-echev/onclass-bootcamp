package com.bootcamp.onclass.domain.api.usecase;


import com.bootcamp.onclass.domain.api.ICapacityServicePort;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.spi.ICapacityPersistencePort;

public class CapacityUseCase implements ICapacityServicePort {
    private ICapacityPersistencePort capacityPersistencePort;
    public CapacityUseCase(ICapacityPersistencePort capacityPersistencePort) {
        this.capacityPersistencePort = capacityPersistencePort;
    }
    @Override
    public void addCapacity(Capacity capacity) {
        capacityPersistencePort.addCapacity(capacity);
    }
}
