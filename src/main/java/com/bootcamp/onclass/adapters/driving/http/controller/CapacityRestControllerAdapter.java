package com.bootcamp.onclass.adapters.driving.http.controller;


import com.bootcamp.onclass.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp.onclass.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.bootcamp.onclass.domain.api.ICapacityServicePort;
import com.bootcamp.onclass.domain.model.Capacity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/capacity")
@RequiredArgsConstructor
public class CapacityRestControllerAdapter {
    private final ICapacityServicePort capacityServicePort;
    private final ICapacityRequestMapper capacityRequestMapper;

    @PostMapping("/addCapacity")
    public ResponseEntity<Capacity> addCapacity (@Valid @RequestBody AddCapacityRequest request){
        return new ResponseEntity<>(capacityServicePort.addCapacity(capacityRequestMapper.addRequestToCapacity(request)), HttpStatus.OK);
    }
}
