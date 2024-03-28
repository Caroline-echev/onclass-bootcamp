package com.bootcamp.onclass.adapters.driven.jpa.mysql.adapter;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.bootcamp.onclass.domain.exception.ElementAlreadyExistsException;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.model.Technology;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CapacityAdapterTest {
    @Mock
    private ICapacityRepository capacityRepository;



    @InjectMocks
    private CapacityAdapter capacityAdapter;

    @Test
    void shouldAddCapacity() {
        // Given
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new Technology(2L, "Node.js", "Entorno para construir servidores escalables en JavaScript"));
        technologies.add(new Technology(3L, "Spring Boot", "Framework Java para desarrollo rápido de aplicaciones"));

        Capacity capacity = new Capacity(1L,
                "Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies);
        when(capacityRepository.findByName(any())).thenReturn(Optional.of(new CapacityEntity()));

        // When-Then
        assertThrows(ElementAlreadyExistsException.class, () -> capacityAdapter.addCapacity(capacity));
        verify(capacityRepository, never()).save(any());
    }
    @Test
    void shouldNotAddDuplicateCapacity() {
        // Given
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new Technology(2L, "Node.js", "Entorno para construir servidores escalables en JavaScript"));
        technologies.add(new Technology(3L, "Spring Boot", "Framework Java para desarrollo rápido de aplicaciones"));

        Capacity capacity = new Capacity(1L,
                "Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies);


        //WHEN

        when(capacityRepository.findByName(any())).thenReturn(Optional.of(new CapacityEntity()));
        assertThrows(ElementAlreadyExistsException.class, () -> capacityAdapter.addCapacity(capacity));

        // THEN

        verify(capacityRepository, never()).save(any());
    }
}