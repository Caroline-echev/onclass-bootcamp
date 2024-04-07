package com.bootcamp.onclass.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "version")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private LocalDate initialDate;

    @Column(nullable = false)
    private  LocalDate finalDate;

    @Column(nullable = false)
    private int maxCapacity;


    @ManyToOne
    @JoinColumn(name = "id_bootcamp")
    private BootcampEntity bootcamp;
}
