package com.bootcamp.onclass.configuration;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.bootcamp.onclass.domain.api.ITechnologyServicePort;
import com.bootcamp.onclass.domain.api.usecase.TechnologyUseCase;
import com.bootcamp.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITechnologyRepository technologyRepository;

    private final ITechnologyEntityMapper technologyEntityMapper;



    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {

        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);

    }
    @Bean
    public ITechnologyServicePort technologyServicePort() {

        return new TechnologyUseCase(technologyPersistencePort());
    }
}