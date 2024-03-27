package com.bootcamp.onclass.adapters.driven.jpa.mysql.repository;


import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICapacityRepository extends JpaRepository<CapacityEntity, Long> {
    @Query("SELECT c FROM CapacityEntity c WHERE LOWER(TRIM(c.name)) = LOWER(TRIM(:name))")
    Optional<CapacityEntity> findByName(String name);
}
