package com.bootcamp.onclass.domain.api.usecase;

import com.bootcamp.onclass.domain.exception.DuplicateItemsListException;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.model.Technology;
import com.bootcamp.onclass.domain.spi.ICapacityPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapacityUseCaseTest {

    @Mock
    private ICapacityPersistencePort capacityPersistencePort;
    @InjectMocks
    private CapacityUseCase capacityUseCase;


    @Test
    @DisplayName("Test successful adding  of a capacity")
    void shouldAddCapacity() {

        //GIVEN

        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new Technology(2L, "Node.js", "Entorno para construir servidores escalables en JavaScript"));
        technologies.add(new Technology(3L, "Spring Boot", "Framework Java para desarrollo rápido de aplicaciones"));
        Capacity capacity = new Capacity(1L,
                "Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies);

        //WHEN

        when(capacityPersistencePort.addCapacity(capacity)).thenReturn(capacity);
        capacityUseCase.addCapacity(capacity);

        //THEN

        verify(capacityPersistencePort).addCapacity(capacity);
    }
    @Test
    @DisplayName("Test exception by duplicate technologies")
    void shouldNotAddCapacityByDuplicateTechnologies(){

        //GIVEN

        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new Technology(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new Technology(3L, "Spring Boot", "Framework Java para desarrollo rápido de aplicaciones"));
        Capacity capacity = new Capacity(1L,
                "Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies);
        // WHEN

        Throwable exception = assertThrows(DuplicateItemsListException.class, () -> {
            capacityUseCase.addCapacity(capacity);
        });

        //THEN
        verify(capacityPersistencePort, never()).addCapacity(any(Capacity.class));
        assertEquals("Duplicate technology in the list", exception.getMessage());


    }

    @Test
    @DisplayName("Expected list of capacities to be returned")
    void shouldGetAllCapacities() {
        // GIVEN

        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new Technology(2L, "Node.js", "Entorno para construir servidores escalables en JavaScript"));
        technologies.add(new Technology(3L, "Spring Boot", "Framework Java para desarrollo rápido de aplicaciones"));

        List<Technology> technologies1 = new ArrayList<>();
        technologies1.add(new Technology(4L, "React.js", "Biblioteca para interfaces de usuario interactivas"));
        technologies1.add(new Technology(5L, "Angular", "Framework para aplicaciones web robustas"));
        technologies1.add(new Technology(3L, "JavaScript", "Agrega interactividad a las páginas web"));

        List<Capacity> capacities = new ArrayList<>();
        capacities.add (new  Capacity(1L,
                "Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies));
        capacities.add (new  Capacity(2L,
                "Desarrollador Frontend",
                "Creación de la interfaz de usuario y experiencia de usuario de una aplicación web o móvil",
                technologies1));

        //WHEN
        when(capacityPersistencePort.getAllCapacities(anyInt(), anyInt(), anyBoolean(),anyBoolean()))
                .thenReturn(capacities);
        List<Capacity> actualCapacities = capacityUseCase.getAllCapacities(0, 10, true, true);

        // THEN
        assertEquals(capacities.size(), actualCapacities.size());
    }
}