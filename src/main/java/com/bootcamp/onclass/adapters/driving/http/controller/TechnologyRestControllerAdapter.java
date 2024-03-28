package com.bootcamp.onclass.adapters.driving.http.controller;


import com.bootcamp.onclass.adapters.driving.http.dto.request.AddTechnologyRequest;

import com.bootcamp.onclass.adapters.driving.http.dto.response.TechnologyResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.bootcamp.onclass.domain.api.ITechnologyServicePort;
import com.bootcamp.onclass.domain.model.Technology;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technology")
@RequiredArgsConstructor
public class TechnologyRestControllerAdapter  {

    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyRequestMapper technologyRequestMapper;
    private final ITechnologyResponseMapper technologyResponseMapper;



        @PostMapping("/addTechnology")
    public ResponseEntity<Technology> addTechnology(@Valid @RequestBody AddTechnologyRequest request) {

        Technology newTechnology = technologyServicePort.addTechnology(technologyRequestMapper.addRequestToTechnology(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(newTechnology);
    }
    @GetMapping("/getTechnologies")
    public ResponseEntity<List<TechnologyResponse>> getAllTechnologies(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "true") boolean orderAsc) {

        List<Technology> technologies = technologyServicePort.getAllTechnologies(page, size, orderAsc);
        List<TechnologyResponse> response = technologyResponseMapper.toTechnologyResponseList(technologies);

        return ResponseEntity.ok(response);
    }

}