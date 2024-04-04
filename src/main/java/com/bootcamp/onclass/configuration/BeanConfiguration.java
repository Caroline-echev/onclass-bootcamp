package com.bootcamp.onclass.configuration;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter.BootcampAdapter;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter.CapacityAdapter;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter.VersionAdapter;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.bootcamp.onclass.domain.api.IBootcampServicePort;
import com.bootcamp.onclass.domain.api.ICapacityServicePort;
import com.bootcamp.onclass.domain.api.ITechnologyServicePort;
import com.bootcamp.onclass.domain.api.IVersionServicePort;
import com.bootcamp.onclass.domain.api.usecase.BootcampUseCase;
import com.bootcamp.onclass.domain.api.usecase.CapacityUseCase;
import com.bootcamp.onclass.domain.api.usecase.TechnologyUseCase;
import com.bootcamp.onclass.domain.api.usecase.VersionUseCase;
import com.bootcamp.onclass.domain.spi.IBootcampPersistencePort;
import com.bootcamp.onclass.domain.spi.ICapacityPersistencePort;
import com.bootcamp.onclass.domain.spi.ITechnologyPersistencePort;
import com.bootcamp.onclass.domain.spi.IVersionPersistencePort;
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

    private final IBootcampRepository bootcampRepository;

    private final IBootcampEntityMapper bootcampEntityMapper;

    private final IVersionRepository versionRepository;

    private final IVersionEntityMapper versionEntityMapper;

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



    @Bean
    public IBootcampPersistencePort bootcampPersistencePort(){
        return new BootcampAdapter(bootcampRepository, bootcampEntityMapper);
    }
    @Bean
    public IBootcampServicePort bootcampServicePort(){
        return new BootcampUseCase(bootcampPersistencePort());
    }


    @Bean
    public IVersionPersistencePort versionPersistencePort(){
        return new VersionAdapter(versionRepository, versionEntityMapper, bootcampRepository, bootcampEntityMapper);
    }
    @Bean
    public IVersionServicePort versionServicePort(){
        return new VersionUseCase(versionPersistencePort());
    }



}
