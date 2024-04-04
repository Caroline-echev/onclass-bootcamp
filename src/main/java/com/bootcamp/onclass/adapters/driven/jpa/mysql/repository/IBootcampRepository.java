package com.bootcamp.onclass.adapters.driven.jpa.mysql.repository;


import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IBootcampRepository  extends JpaRepository<BootcampEntity, Long> {
    Optional<BootcampEntity> findByName(String name);
    Optional<BootcampEntity> findById(Long id);

    @Query("SELECT b FROM BootcampEntity b ORDER BY SIZE(b.capacities) ASC")
    Page<BootcampEntity> findAllOrderedByCapacitySizeAsc(Pageable pageable);
    @Query("SELECT b FROM BootcampEntity b ORDER BY SIZE(b.capacities) DESC")
    Page<BootcampEntity> findAllOrderedByCapacitySizeDesc(Pageable pageable);
}
