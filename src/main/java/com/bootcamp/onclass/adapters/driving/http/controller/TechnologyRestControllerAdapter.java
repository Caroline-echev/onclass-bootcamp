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

@RestController
@RequestMapping("/technology")
@RequiredArgsConstructor
public class TechnologyRestControllerAdapter  {

    private final ITechnologyServicePort technologyServicePort;

    private final ITechnologyRequestMapper technologyRequestMapper;




    @PostMapping("/")
    public ResponseEntity<Technology> addTechnology(@Valid @RequestBody AddTechnologyRequest request) {

        Technology newTechnology = technologyServicePort.addTechnology(technologyRequestMapper.addRequestToTechnology(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(newTechnology);
    }

}