package com.bootcamp.onclass.adapters.driven.jpa.mysql.entity;

import com.bootcamp.onclass.domain.model.Bootcamp;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "capacity")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CapacityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "capacity_technology",
            joinColumns = @JoinColumn(name = "capacity_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id", referencedColumnName = "id")
    )
    private List<TechnologyEntity> technologies;
    @ManyToMany(mappedBy = "capacities")
    private List<BootcampEntity> bootcamps;

    public CapacityEntity(long l, String desarrolladorBackend, String s, Object o) {
    }
}
