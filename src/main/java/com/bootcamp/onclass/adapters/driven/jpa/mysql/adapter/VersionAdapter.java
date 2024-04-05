package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.model.Version;
import com.bootcamp.onclass.domain.spi.IVersionPersistencePort;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class VersionAdapter implements IVersionPersistencePort {
    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;
    @Override
    public Version addVersion(Version version) {
        validateBootcamp(version);
        return versionEntityMapper.toModel(versionRepository.save(versionEntityMapper.toEntity(version)));
    }
    private void validateBootcamp(Version version) {
        BootcampEntity bootcamp = bootcampRepository.findById(version.getBootcamp().getId())
                .orElseThrow(() -> new NoDataFoundException(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE));
        version.setBootcamp(bootcampEntityMapper.toModel(bootcamp));
    }
}
