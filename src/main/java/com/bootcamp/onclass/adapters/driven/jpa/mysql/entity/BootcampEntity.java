package com.bootcamp.onclass.adapters.driven.jpa.mysql.entity;

import com.bootcamp.onclass.domain.model.Capacity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "bootcamp")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BootcampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "bootcamp_capacity",
            joinColumns = @JoinColumn(name = "bootcamp_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "capacity_id", referencedColumnName = "id")
    )
    private List<CapacityEntity> capacities;
}
