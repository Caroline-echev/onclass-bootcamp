package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.technology.TechnologyResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.bootcamp.onclass.domain.api.ITechnologyServicePort;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @DisplayName("Test successful adding  of a technology")
    void testAddTechnologyRest() {

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
    @Test
    @DisplayName("Expected list of Technologies to be returned")
    void testGetAllTechnologies() {
        // GIVEN
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming language"));
        technologies.add(new Technology(2L, "Python", "Scripting language"));

        List<TechnologyResponse> expectedResponse = new ArrayList<>();
        expectedResponse.add(new TechnologyResponse(1L, "Java", "Programming language"));
        expectedResponse.add(new TechnologyResponse(2L, "Python", "Scripting language"));



        // WHEN
        when(technologyServicePort.getAllTechnologies(0, 10, true)).thenReturn(technologies);
        when(technologyResponseMapper.toTechnologyResponseList(technologies)).thenReturn(expectedResponse);
        ResponseEntity<List<TechnologyResponse>> responseEntity = controller.getAllTechnologies(0, 10, true);

        // THEN
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

}