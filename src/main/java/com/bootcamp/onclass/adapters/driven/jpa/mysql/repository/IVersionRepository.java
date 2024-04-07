package com.bootcamp.onclass.adapters.driven.jpa.mysql.repository;


import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IVersionRepository extends JpaRepository<VersionEntity, Long> {
    Page<VersionEntity> findByBootcampId(Long bootcampId, Pageable pageable);

}
