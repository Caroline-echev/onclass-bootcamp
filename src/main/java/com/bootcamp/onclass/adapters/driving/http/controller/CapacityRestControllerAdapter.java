package com.bootcamp.onclass.adapters.driving.http.controller;


import com.bootcamp.onclass.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.CapacityResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.bootcamp.onclass.domain.api.ICapacityServicePort;
import com.bootcamp.onclass.domain.model.Capacity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/capacity")
@RequiredArgsConstructor
public class CapacityRestControllerAdapter {
    private final ICapacityServicePort capacityServicePort;
    private final ICapacityRequestMapper capacityRequestMapper;
    private final ICapacityResponseMapper capacityResponseMapper;

    @PostMapping("/addCapacity")
    public ResponseEntity<Capacity> addCapacity (@Valid @RequestBody AddCapacityRequest request){
        return new ResponseEntity<>(capacityServicePort.addCapacity(capacityRequestMapper.addRequestToCapacity(request)), HttpStatus.OK);
    }
    @GetMapping("/getCapacities")
    public ResponseEntity<List<CapacityResponse>> getAllCapacities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "true") boolean isAsc,
            @RequestParam(defaultValue = "true") boolean orderName) {
        List<Capacity> capacities = capacityServicePort.getAllCapacities(page, size, isAsc, orderName);
        List<CapacityResponse> response = capacities.stream().map(capacityResponseMapper::modelToFindResponse).toList();

        return ResponseEntity.ok().body(response);
    }
}
