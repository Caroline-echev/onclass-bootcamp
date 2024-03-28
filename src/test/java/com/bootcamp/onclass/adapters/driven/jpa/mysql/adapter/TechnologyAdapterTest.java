package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.model.Technology;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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

    @Test
    @DisplayName("Test successful adding  of a technology")
    void shouldAddTechnology() {
        // GIVEN

        Technology technology = new Technology(10L, "Python", "Biblioteca para interfaces de usuario dinámicas.");


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

        Technology technology = new Technology(10L, "Python", "Biblioteca para interfaces de usuario dinámicas.");

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

        Technology technology = new Technology(10L, "Python", "Biblioteca para interfaces de usuario dinámicas.");
        String technologyName = "Python";
        TechnologyEntity technologyEntity = new TechnologyEntity();

        // WHEN

        when(technologyRepository.findByName(technologyName)).thenReturn(Optional.of(technologyEntity));
        when(technologyEntityMapper.toModel(technologyEntity)).thenReturn(technology);
        Optional<Technology> result = technologyAdapter.getTechnologyByName(technologyName);

        // THEN

        assertTrue(result.isPresent());
        assertEquals(technology, result.get());
    }

    @Test
    @DisplayName("Expected Optional of Technology to be empty")
    void shouldNotGetTechnologyByName() {
        // GIVEN

            String technologyName = "Python";
        when(technologyRepository.findByName(technologyName)).thenReturn(Optional.empty());

        // WHEN

        Optional<Technology> result = technologyAdapter.getTechnologyByName(technologyName);

        // THEN

        assertFalse(result.isPresent());
    }


    @Test
    @DisplayName("Expected list of Technologies to be returned")
    void shouldGetAllTechnologies() {
        // GIVEN

        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming language"));
        technologies.add(new Technology(2L, "Python", "Scripting language"));
        technologies.add(new Technology(3L, "JavaScript", "Programming language"));

        int page = 0;
        int size = 10;
        boolean orderAsc = true;
        List<TechnologyEntity> technologyEntities = new ArrayList<>();
        technologyEntities.add(new TechnologyEntity());


        // WHEN

        when(technologyRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(technologyEntities));
        when(technologyEntityMapper.toModelList(technologyEntities)).thenReturn(technologies);
        List<Technology> result = technologyAdapter.getAllTechnologies(page, size, orderAsc);

        // THEN

        assertFalse(result.isEmpty());
        assertEquals(technologies, result);
    }

    @Test
    @DisplayName("Expected empty list of Technologies to be returned")
    void shouldGetEmptyTechnologies(){
        // GIVEN
        int page = 0;
        int size = 10;
        boolean orderAsc = true;
        when(technologyRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));

        // WHEN - THEN
        assertThrows(NoDataFoundException.class, () -> technologyAdapter.getAllTechnologies(page, size, orderAsc));
    }

}