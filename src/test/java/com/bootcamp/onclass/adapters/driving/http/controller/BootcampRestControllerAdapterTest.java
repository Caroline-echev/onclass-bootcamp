package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.bootcamp.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.IBootcampResponseMapper;
import com.bootcamp.onclass.data.BootcampData;
import com.bootcamp.onclass.data.ParametersData;
import com.bootcamp.onclass.domain.api.IBootcampServicePort;
import com.bootcamp.onclass.domain.model.Bootcamp;
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
class BootcampRestControllerAdapterTest {

    @Mock
    private IBootcampServicePort bootcampServicePort ;

    @Mock
    private IBootcampRequestMapper bootcampRequestMapper ;
    @Mock
    private IBootcampResponseMapper bootcampResponseMapper ;
    @InjectMocks
    private BootcampRestControllerAdapter controller;

    private BootcampData bootcampData = new BootcampData();
    @Test
    @DisplayName("Test successful adding  of a bootcamp")
    void addBootcamp() {

        //GIVEN

        Bootcamp bootcamp = bootcampData.createBootcamp();

        AddBootcampRequest  request = bootcampData.createAddBootcampRequest();
        when(bootcampRequestMapper.addRequestToBootcamp(request)).thenReturn(bootcamp);
        when(bootcampServicePort.addBootcamp(bootcamp)).thenReturn(bootcamp);

        // WHEN
        ResponseEntity<Bootcamp> responseEntity = controller.addBootcamp(request);

        // THEN
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bootcamp, responseEntity.getBody());
    }
    @Test
    @DisplayName("Expected list of bootcamps to be returned")
    void GetAllBootcamps() {
        // GIVEN

        List<Bootcamp> bootcamps = bootcampData.createBootcamps();

        // WHEN

        when(bootcampServicePort
                .getAllBootcamps(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME)).thenReturn(bootcamps);
        controller.getAllBootcamps(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME);

        // THEN

        verify(bootcampServicePort).getAllBootcamps(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC, ParametersData.ORDER_NAME);
        verify(bootcampResponseMapper, times(bootcamps.size())).modelToFindResponse(any());
    }
}