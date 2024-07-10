package com.bootcamp.onclass.adapters.driving.http.controller;


import com.bootcamp.onclass.adapters.driving.http.dto.request.AddTechnologyRequest;

import com.bootcamp.onclass.adapters.driving.http.dto.response.technology.TechnologyResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.bootcamp.onclass.domain.api.ITechnologyServicePort;
import com.bootcamp.onclass.domain.model.Technology;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technology")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication")
@SecurityRequirement(name = "bearer-key")
@CrossOrigin(origins = "http://localhost:4200")
public class TechnologyRestControllerAdapter  {

    private final ITechnologyServicePort technologyServicePort;
    private final ITechnologyRequestMapper technologyRequestMapper;
    private final ITechnologyResponseMapper technologyResponseMapper;


    @ApiResponse(responseCode = "200", description = "Admin registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addTechnology")
    public ResponseEntity<Technology> addTechnology(@Valid @RequestBody AddTechnologyRequest request) {

        Technology newTechnology = technologyServicePort.addTechnology(technologyRequestMapper.addRequestToTechnology(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(newTechnology);
    }
    @ApiResponse(responseCode = "200", description = "Admin registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getTechnologies")
    public ResponseEntity<Page<TechnologyResponse>> getAllTechnologies(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "true") boolean orderAsc) {

        Page<Technology> technologies = technologyServicePort.getAllTechnologies(page, size, orderAsc);

        return ResponseEntity.ok(technologies.map(technologyResponseMapper::toResponse));
    }

}