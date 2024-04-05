package com.bootcamp.onclass.adapters.driven.jpa.mysql.entity;

import com.bootcamp.onclass.domain.model.Bootcamp;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
