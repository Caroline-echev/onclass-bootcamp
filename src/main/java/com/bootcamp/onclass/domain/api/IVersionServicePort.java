package com.bootcamp.onclass.domain.api;

import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.model.Version;

import java.util.List;

public interface IVersionServicePort {

    Version addVersion(Version version);
    List<Version> getAllVersionByBootcamp(List<Long> bootcampIds, Integer page, Integer size, boolean orderAsc, boolean orderDate);

}
