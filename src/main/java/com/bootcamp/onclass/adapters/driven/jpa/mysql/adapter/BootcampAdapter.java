package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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

    @Override
    public List<Bootcamp> getAllBootcamps(Integer page, Integer size, boolean orderAsc, boolean orderName) {
        Sort sort;
        Pageable pageable;
        List<BootcampEntity> bootcamps = null;
        if (orderName) {
            sort = orderAsc ? Sort.by("name").ascending() : Sort.by("name").descending();
            pageable = PageRequest.of(page, size, sort);
            bootcamps = bootcampRepository.findAll(pageable).getContent();
        } else {
            pageable = PageRequest.of(page, size);
            bootcamps = orderAsc ? bootcampRepository.findAllOrderedByCapacitySizeAsc(pageable).getContent() :
                    bootcampRepository.findAllOrderedByCapacitySizeDesc(pageable).getContent();
        }

        if (bootcamps.isEmpty()) {
            throw new NoDataFoundException(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        }

        return bootcampEntityMapper.toModelList(bootcamps);
    }

}
