package com.bootcamp.onclass.domain.api.usecase;


import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.api.ITechnologyServicePort;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.spi.ITechnologyPersistencePort;


import java.util.List;
import java.util.Optional;

public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {

        this.technologyPersistencePort = technologyPersistencePort;

    }

    @Override
    public Technology addTechnology(Technology technology) {

        if(isUniqueName(technology)){
            throw new ElementAlreadyExistsException(Constants.ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE);
        }

        return technologyPersistencePort.addTechnology(technology);

    }

    @Override
    public Optional<Technology> getTechnologyByName(String name) {
        Optional<Technology> technologyOptional = technologyPersistencePort.getTechnologyByName(name);
        if (technologyOptional.isEmpty()) {
            throw new NoDataFoundException(name);
        }
        return technologyOptional;
    }


    private boolean isUniqueName(Technology technology) {
        return technologyPersistencePort.getTechnologyByName(technology.getName()).isPresent();
    }
    @Override
    public List<Technology> getAllTechnologies(Integer page, Integer size, boolean orderAsc) {
        return technologyPersistencePort.getAllTechnologies(page, size, orderAsc);

    }
}
