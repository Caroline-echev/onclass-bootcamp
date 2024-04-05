package com.bootcamp.onclass.domain.spi;

import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.model.Version;

import java.util.List;

public interface IVersionPersistencePort {

    Version addVersion(Version version);
    List<Version> getAllVersionByBootcamp(List<Long> bootcampIds, Integer page, Integer size, boolean orderAsc, boolean orderDate);

}
