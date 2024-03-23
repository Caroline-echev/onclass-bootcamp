package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.bootcamp.onclass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.bootcamp.onclass.domain.api.ITechnologyServicePort;
import com.bootcamp.onclass.domain.model.Technology;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TechnologyRestControllerAdapterTest {
    @Mock
    private ITechnologyServicePort technologyServicePort ;

    @Mock
    private ITechnologyRequestMapper technologyRequestMapper ;

    @Mock
    private ITechnologyResponseMapper technologyResponseMapper;

    @InjectMocks
    private TechnologyRestControllerAdapter controller;

    @Test
    void addTechnologyRestTest() {

        // GIVEN
        AddTechnologyRequest request = new AddTechnologyRequest("Python", "Biblioteca para interfaces de usuario dinámicas.");
        Technology newTechnology = new Technology(1L, "Python", "Biblioteca para interfaces de usuario dinámicas.");

        when(technologyRequestMapper.addRequestToTechnology(request)).thenReturn(newTechnology);
        when(technologyServicePort.addTechnology(newTechnology)).thenReturn(newTechnology);

        // WHEN
        ResponseEntity<Technology> responseEntity = controller.addTechnology(request);

        // THEN
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(newTechnology, responseEntity.getBody());

        verify(technologyServicePort).addTechnology(newTechnology);


    }
}