package com.bootcamp.onclass.domain.api.usecase;

import com.bootcamp.onclass.data.ParametersData;
import com.bootcamp.onclass.data.TechnologyData;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.exception.NoDataFoundException;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.spi.ITechnologyPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnologyUseCaseTest {
    @Mock
    private ITechnologyPersistencePort technologyPersistencePort;

    @InjectMocks
    private TechnologyUseCase technologyUseCase;

    private TechnologyData technologyData = new TechnologyData();

    @Test
    @DisplayName("Test successful adding  of a technology")
    void shouldAddTechnology() {

        //GIVEN

        Technology technology = technologyData.createTechnology();

        //WHEN

        when(technologyPersistencePort.addTechnology(technology)).thenReturn(technology);
        technologyUseCase.addTechnology(technology);

        //THEN

        verify(technologyPersistencePort).addTechnology(technology);

    }

    @Test
    @DisplayName("Test unsuccessful adding of a technology")
    void shouldNotAddDuplicateTechnology() {
        // GIVEN

        Technology technology = technologyData.createTechnology();
        when(technologyPersistencePort.getTechnologyByName(technologyData.TECHNOLOGY_NAME)).thenReturn(Optional.of(technology));

        // WHEN

        assertThrows(ElementAlreadyExistsException.class, () -> {
            technologyUseCase.addTechnology(technology);
        });

        // THEN

        verify(technologyPersistencePort, never()).addTechnology(any(Technology.class));

    }

    @Test
    @DisplayName("Test getting technology by name")
    void shouldGetTechnologyByName() {
        // GIVEN
        Technology technology = technologyData.createTechnology();

        // WHEN
        when(technologyPersistencePort.getTechnologyByName(technologyData.TECHNOLOGY_NAME)).thenReturn(Optional.of(technology));
        Optional<Technology> returnedTechnology = technologyUseCase.getTechnologyByName(technologyData.TECHNOLOGY_NAME);

        // THEN
        assertTrue(returnedTechnology.isPresent());
        assertEquals(technology, returnedTechnology.get());
    }

    @Test
    @DisplayName("Test getting non-existing technology by name")
    void shouldThrowNoDataFoundException() {
        // WHEN
        when(technologyPersistencePort.getTechnologyByName(technologyData.NOT_EXISTING_TECHNOLOGY)).thenReturn(Optional.empty());

        // THEN
        assertThrows(NoDataFoundException.class, () -> technologyUseCase
                .getTechnologyByName(technologyData.NOT_EXISTING_TECHNOLOGY));
    }

    @Test
    @DisplayName("Expected list of Technologies to be returned")
    void shouldGetAllTechnologies() {

        // GIVEN
        List<Technology> technologies = technologyData.createTechnologies();
        //WHEN
        when(technologyPersistencePort.getAllTechnologies(anyInt(), anyInt(), anyBoolean()))
                        .thenReturn(technologies);
        List<Technology> actualTechnologies = technologyUseCase.getAllTechnologies
                (ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC);

        // THEN
        assertEquals(technologies.size(), actualTechnologies.size());
    }

    @Test
    @DisplayName("Expected empty list of Technologies to be returned")
    void shouldGetEmptyTechnologies() {
        // GIVEN
        List<Technology> emptyList = new ArrayList<>();

        // WHEN
        when(technologyPersistencePort.getAllTechnologies(anyInt(), anyInt(), anyBoolean()))
                .thenReturn(emptyList);
        List<Technology> actualTechnologies = technologyUseCase
                .getAllTechnologies(ParametersData.PAGE, ParametersData.SIZE, ParametersData.ORDER_ASC);

        // THEN
        assertTrue(actualTechnologies.isEmpty(), "Expected empty list of Technologies to be returned");
    }

}