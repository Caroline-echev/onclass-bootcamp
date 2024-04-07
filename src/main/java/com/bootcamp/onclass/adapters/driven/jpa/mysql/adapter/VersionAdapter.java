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
        version.setBootcamp(validateBootcamp(version.getBootcamp().getId()));
        return versionEntityMapper.toModel(versionRepository.save(versionEntityMapper.toEntity(version)));
    }

    @Override
    public List<Version> getAllVersionByBootcamp(Long bootcampId, Integer page, Integer size, boolean orderAsc, String orderType) {
        Pageable pageable = createSort(page, size, orderAsc, orderType);
        List<VersionEntity> versions;
        if (bootcampId == null) {
            versions = versionRepository.findAll(pageable).getContent();
        } else {
            versions = versionRepository.findByBootcampId(bootcampId, pageable).getContent();
        }
        if (versions.isEmpty()) {
            throw new NoDataFoundException(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE);
        }


        return versionEntityMapper.toModelList(versions);
    }

    public static Pageable createSort(int page, int size, boolean orderAsc, String orderType) {
        Sort sort = orderAsc ? Sort.by(orderType).ascending() : Sort.by(orderType).descending();
        return PageRequest.of(page, size, sort);
    }

    private Bootcamp validateBootcamp(Long bootcampId) {
        BootcampEntity bootcamp = bootcampRepository.findById(bootcampId)
                .orElseThrow(() -> new NoDataFoundException(Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE));
        return bootcampEntityMapper.toModel(bootcamp);
    }
}
