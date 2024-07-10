package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.bootcamp.onclass.data.ParametersData;
import com.bootcamp.onclass.data.TechnologyData;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.model.Technology;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnologyAdapterTest {
    @Mock
    private ITechnologyRepository technologyRepository;

    @Mock
    private ITechnologyEntityMapper technologyEntityMapper;

    @InjectMocks
    private TechnologyAdapter technologyAdapter;

    private static TechnologyData technologyData = new TechnologyData();
    @Test
    @DisplayName("Test successful adding  of a technology")
    void shouldAddTechnology() {
        // GIVEN

        Technology technology = technologyData.createTechnology();

        // WHEN

        when(technologyRepository.findByName(any())).thenReturn(Optional.empty());
        technologyAdapter.addTechnology(technology);

        // THEN

        verify(technologyRepository, times(1)).save(any());
    }
    @Test
    @DisplayName("Test unsuccessful adding of a technology")
    void shouldNotAddDuplicateTechnology() {
        // GIVEN

        Technology technology = technologyData.createTechnology();

        //WHEN

        when(technologyRepository.findByName(any())).thenReturn(Optional.of(new TechnologyEntity()));
        assertThrows(ElementAlreadyExistsException.class, () -> {
            technologyAdapter.addTechnology(technology);
        });

        // THEN

        verify(technologyRepository, never()).save(any());
    }

    @Test
    @DisplayName("Expected of Technology to be present")
    void shouldGetTechnologyByName() {
        // GIVEN

        Technology technology = technologyData.createTechnology();

        TechnologyEntity technologyEntity = technologyData.createTechnologyEntity();

        // WHEN

        when(technologyRepository.findByName(technologyData.TECHNOLOGY_NAME)).thenReturn(Optional.of(technologyEntity));
        when(technologyEntityMapper.toModel(technologyEntity)).thenReturn(technology);
        Optional<Technology> result = technologyAdapter.getTechnologyByName(technologyData.TECHNOLOGY_NAME);

        // THEN

        assertTrue(result.isPresent());
        assertEquals(technology, result.get());
    }

    @Test
    @DisplayName("Expected Optional of Technology to be empty")
    void shouldNotGetTechnologyByName() {
        // GIVEN

        when(technologyRepository.findByName(technologyData.TECHNOLOGY_NAME)).thenReturn(Optional.empty());

        // WHEN

        Optional<Technology> result = technologyAdapter.getTechnologyByName(technologyData.TECHNOLOGY_NAME);

        // THEN

        assertFalse(result.isPresent());
    }


    @Test
    @DisplayName("Expected list of Technologies to be returned")
    void shouldGetAllTechnologies() {
        // GIVEN

        List<Technology> technologies = technologyData.createTechnologies();
        List<TechnologyEntity> technologyEntities = technologyData.createTechnologyEntities();

        // WHEN

        when(technologyRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(technologyEntities));
        when(technologyEntityMapper.toModelList(technologyEntities)).thenReturn(technologies);
        Page<Technology> result = technologyAdapter
                .getAllTechnologies(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC);

        // THEN

        assertFalse(result.isEmpty());
        assertEquals(technologies, result);
    }

    @Test
    @DisplayName("Expected empty list of Technologies to be returned")
    void shouldGetEmptyTechnologies(){
        // GIVEN

        when(technologyRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));

        // WHEN - THEN
        assertThrows(NoDataFoundException.class, () -> technologyAdapter
                .getAllTechnologies(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC));
    }
    @Test
    @DisplayName("Test getting all technologies sorted ascending by name")
    void shouldGetAllTechnologiesSortedAscendingByName() {
        // GIVEN

        List<Technology> technologies = technologyData.createTechnologies();

        Sort sort = Sort.by(ParametersData.NAME).ascending();

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE, sort);

        List<TechnologyEntity> technologyEntities = technologyData.createTechnologyEntities();

        // WHEN

        when(technologyRepository.findAll(pageable)).thenReturn(new PageImpl<>(technologyEntities));
        when(technologyEntityMapper.toModelList(technologyEntities)).thenReturn(technologies);

        Page<Technology> result = technologyAdapter
                .getAllTechnologies(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC);

        // THEN

        assertFalse(result.isEmpty());
        assertEquals(technologies, result);
        verify(technologyRepository).findAll(pageable);
    }
    @Test
    @DisplayName("Test getting all technologies sorted descending by name")
    void shouldGetAllTechnologiesSortedDescendingByName() {
        // GIVEN


        List<Technology> technologies = technologyData.createTechnologies();

        Sort sort = Sort.by(ParametersData.NAME).descending();

        Pageable pageable = PageRequest.of(ParametersData.PAGE, ParametersData.SIZE, sort);

        List<TechnologyEntity> technologyEntities = technologyData.createTechnologyEntities();

        // WHEN

        when(technologyRepository.findAll(pageable)).thenReturn(new PageImpl<>(technologyEntities));
        when(technologyEntityMapper.toModelList(technologyEntities)).thenReturn(technologies);

        Page<Technology> result = technologyAdapter
                .getAllTechnologies(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_DESC);

        // THEN
        assertFalse(result.isEmpty());
        assertEquals(technologies, result);

        verify(technologyRepository).findAll(pageable);
    }
}
