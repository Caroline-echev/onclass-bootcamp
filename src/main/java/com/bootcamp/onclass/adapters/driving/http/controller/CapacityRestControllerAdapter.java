package com.bootcamp.onclass.adapters.driving.http.controller;


import com.bootcamp.onclass.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.capacity.CapacityResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.bootcamp.onclass.domain.api.ICapacityServicePort;
import com.bootcamp.onclass.domain.model.Capacity;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/capacity")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication")
@SecurityRequirement(name = "bearer-key")
public class CapacityRestControllerAdapter {
    private final ICapacityServicePort capacityServicePort;
    private final ICapacityRequestMapper capacityRequestMapper;
    private final ICapacityResponseMapper capacityResponseMapper;

    @ApiResponse(responseCode = "200", description = "Admin registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addCapacity")
    public ResponseEntity<Capacity> addCapacity (@Valid @RequestBody AddCapacityRequest request){
        return new ResponseEntity<>(capacityServicePort.addCapacity(capacityRequestMapper.addRequestToCapacity(request)), HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Admin registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not found")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getCapacities")
    public ResponseEntity<List<CapacityResponse>> getAllCapacities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "true") boolean orderAsc,
            @RequestParam(defaultValue = "false") boolean orderName) {
        List<Capacity> capacities = capacityServicePort.getAllCapacities(page, size, orderAsc, orderName);
        List<CapacityResponse> response = capacities.stream().map(capacityResponseMapper::modelToFindResponse).toList();

        return ResponseEntity.ok().body(response);
    }
}
