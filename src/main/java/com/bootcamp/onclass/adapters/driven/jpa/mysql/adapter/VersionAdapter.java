package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.bootcamp.onclass.configuration.Constants;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.model.Version;
import com.bootcamp.onclass.domain.spi.IVersionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


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

    @Override
    public List<Version> getAllVersionByBootcamp(List<Long> bootcampIds, Integer page, Integer size, boolean orderAsc, boolean orderDate) {
        Sort sort;

        List<VersionEntity> versions = null;
        if (orderDate) {
            sort = orderAsc ? Sort.by("initialDate").ascending() : Sort.by("initialDate").descending();
        } else {
            sort = orderAsc ? Sort.by("maxCapacity").ascending() : Sort.by("maxCapacity").descending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);


        if (bootcampIds.isEmpty()) {
            versions = versionRepository.findAll(pageable).getContent();
        } else {
            versions = versionRepository.findByBootcampIdIn(bootcampIds, pageable); }

        return versionEntityMapper.toModelList(versions);
    }

    private void validateBootcamp(Version version) {
        BootcampEntity bootcamp = bootcampRepository.findById(version.getBootcamp().getId())
                .orElseThrow(() -> new NoDataFoundException(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE));
        version.setBootcamp(bootcampEntityMapper.toModel(bootcamp));
    }
}
