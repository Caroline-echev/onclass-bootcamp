package com.bootcamp.onclass.domain.api.usecase;


import com.bootcamp.onclass.domain.api.ITechnologyServicePort;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.exception.NotUniqueNameException;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.spi.ITechnologyPersistencePort;


import java.util.Optional;

public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort technologyPersistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort technologyPersistencePort) {

        this.technologyPersistencePort = technologyPersistencePort;

    }

    @Override
    public Technology addTechnology(Technology technology) {

        if(!isUniqueName(technology)){
            throw new NotUniqueNameException("The name of the technology already exists");
        }

        return technologyPersistencePort.addTechnology(technology);

    }

    @Override
    public Optional<Technology> getTechnologyByName(String name) {

        if (technologyPersistencePort.getTechnologyByName(name) == null) {
            throw new NoDataFoundException(name);
        }
        return technologyPersistencePort.getTechnologyByName(name);
    }


    private  boolean isUniqueName(Technology technology){

        if (technologyPersistencePort.getTechnologyByName(technology.getName()) == null){
            return false;
        }
        return true;
    }
}
