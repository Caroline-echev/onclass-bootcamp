package com.bootcamp.onclass.domain.api.usecase;

import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
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


    @Test
    @DisplayName("Test successful adding  of a technology")
    void shouldAddTechnology() {

        //GIVEN

        Technology technology = new Technology(10L, "Python", "Biblioteca para interfaces de usuario dinámicas.");

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

        Technology technology = new Technology(10L, "Python", "Biblioteca para interfaces de usuario dinámicas.");
        when(technologyPersistencePort.getTechnologyByName("Python")).thenReturn(Optional.of(technology));

        // WHEN

        Throwable exception = assertThrows(ElementAlreadyExistsException.class, () -> {
            technologyUseCase.addTechnology(technology);
        });

        // THEN

        verify(technologyPersistencePort, never()).addTechnology(any(Technology.class));

    }
    @Test
    @DisplayName("Expected Optional of Technology to be present")
    void shouldGetTechnologyByNameTest() {

        //GIVEN
        Technology technology = new Technology(10L, "Python", "Biblioteca para interfaces de usuario dinámicas.");
        technologyUseCase.addTechnology(technology);

        //WHEN

        when(technologyPersistencePort.getTechnologyByName("Python")).thenReturn(Optional.of(technology));
        Optional<Technology> result = technologyPersistencePort.getTechnologyByName("Python");

        //THEN
        assertTrue(result.isPresent());

    }
    @Test
    @DisplayName("Expected Optional of Technology to be empty")
    void shouldNotGetTechnologyByName() {

        // GIVEN
        String name = "Java";

        // WHEN
        when(technologyPersistencePort.getTechnologyByName(name)).thenReturn(Optional.empty());
        Optional<Technology> result = technologyPersistencePort.getTechnologyByName(name);

        // THEN
        assertFalse(result.isPresent());


    }
    @Test
    @DisplayName("Expected list of Technologies to be returned")
    void shouldGetAllTechnologies() {

        // GIVEN
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new Technology(2L, "Node.js", "Entorno para construir servidores escalables en JavaScript"));
        technologies.add(new Technology(3L, "Spring Boot", "Framework Java para desarrollo rápido de aplicaciones"));

        //WHEN
        when(technologyPersistencePort.getAllTechnologies(anyInt(), anyInt(), anyBoolean()))
                        .thenReturn(technologies);
        List<Technology> actualTechnologies = technologyUseCase.getAllTechnologies(0, 10, true);

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
        List<Technology> actualTechnologies = technologyUseCase.getAllTechnologies(0, 10, true);

        // THEN
        assertTrue(actualTechnologies.isEmpty(), "Expected empty list of Technologies to be returned");
    }
}