package com.bootcamp.onclass.adapters.driving.http.dto.response.version;

import com.bootcamp.onclass.adapters.driving.http.dto.response.bootcamp.BootcampNameResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
public class VersionResponse {
    private  final Long id;
    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final  int maxCapacity;
    private final BootcampNameResponse bootcamp;
}
