package com.bootcamp.onclass.configuration;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter.CapacityAdapter;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.bootcamp.onclass.domain.api.ICapacityServicePort;
import com.bootcamp.onclass.domain.api.ITechnologyServicePort;
import com.bootcamp.onclass.domain.api.usecase.CapacityUseCase;
import com.bootcamp.onclass.domain.api.usecase.TechnologyUseCase;
import com.bootcamp.onclass.domain.spi.ICapacityPersistencePort;
import com.bootcamp.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITechnologyRepository technologyRepository;

    private final ITechnologyEntityMapper technologyEntityMapper;

    private final ICapacityRepository capacityRepository;

    private final ICapacityEntityMapper capacityEntityMapper;

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {

        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);

    }
    @Bean
    public ITechnologyServicePort technologyServicePort() {

        return new TechnologyUseCase(technologyPersistencePort());
    }
    @Bean
    public ICapacityPersistencePort capacityPersistencePort(){
        return new CapacityAdapter(capacityRepository, capacityEntityMapper);
    }
    @Bean
    public ICapacityServicePort capacityServicePort(){
        return new CapacityUseCase(capacityPersistencePort());
    }

}
