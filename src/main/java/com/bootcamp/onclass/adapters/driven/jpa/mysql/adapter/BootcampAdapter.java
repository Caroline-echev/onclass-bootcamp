package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BootcampAdapter implements IBootcampPersistencePort {

    private final IBootcampRepository bootcampRepository;

    private final IBootcampEntityMapper bootcampEntityMapper;

    @Override
    public Bootcamp addBootcamp(Bootcamp bootcamp) {

       if (bootcampRepository.findByName(bootcamp.getName()).isPresent()) {
           throw new ElementAlreadyExistsException(Constants.ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE);
       }
        bootcampRepository.save(bootcampEntityMapper.toEntity(bootcamp));

        return bootcamp;

    }
}
