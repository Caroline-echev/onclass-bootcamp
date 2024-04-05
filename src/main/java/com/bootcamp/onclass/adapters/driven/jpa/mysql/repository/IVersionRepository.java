package com.bootcamp.onclass.adapters.driven.jpa.mysql.repository;


import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IVersionRepository extends JpaRepository<VersionEntity, Long> {
    List<VersionEntity> findByBootcampIdIn(List<Long> bootcampIds, Pageable pageable);

}
