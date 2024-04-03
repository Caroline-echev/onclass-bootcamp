package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp.onclass.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.bootcamp.onclass.data.CapacityData;
import com.bootcamp.onclass.data.ParametersData;
import com.bootcamp.onclass.domain.api.ICapacityServicePort;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.model.Technology;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapacityRestControllerAdapterTest {
    @Mock
    private ICapacityServicePort capacityServicePort ;

    @Mock
    private ICapacityRequestMapper capacityRequestMapper ;
    @Mock
    private ICapacityResponseMapper capacityResponseMapper ;
    @InjectMocks
    private CapacityRestControllerAdapter controller;

    private CapacityData capacityData = new CapacityData();
    @Test
    @DisplayName("Test successful adding  of a capacity")
    void addCapacity() {

        //GIVEN

        Capacity capacity = capacityData.createCapacity();

        AddCapacityRequest request = capacityData.createAddCapacityRequest();

        when(capacityRequestMapper.addRequestToCapacity(request)).thenReturn(capacity);
        when(capacityServicePort.addCapacity(capacity)).thenReturn(capacity);

        // WHEN
        ResponseEntity<Capacity> responseEntity = controller.addCapacity(request);

        // THEN
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(capacity, responseEntity.getBody());
    }
    @Test
    @DisplayName("Expected list of capacities to be returned")
    void GetAllCapacities() {
        // GIVEN

        List<Capacity> capacities = capacityData.createCapacities();


        // WHEN

        when(capacityServicePort
                .getAllCapacities(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME))
                .thenReturn(capacities);
        controller.getAllCapacities(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME);

        // THEN

        verify(capacityServicePort).getAllCapacities(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME);
        verify(capacityResponseMapper, times(capacities.size())).modelToFindResponse(any());
    }

}