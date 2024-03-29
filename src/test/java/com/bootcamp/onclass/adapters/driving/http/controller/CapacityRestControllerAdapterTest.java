package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.CapacityResponse;
import com.bootcamp.onclass.adapters.driving.http.dto.response.TechnologiesCapacityResponse;
import com.bootcamp.onclass.adapters.driving.http.dto.response.TechnologyResponse;
import com.bootcamp.onclass.adapters.driving.http.mapper.ICapacityRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.ICapacityResponseMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.ITechnologyRequestMapper;
import com.bootcamp.onclass.adapters.driving.http.mapper.ITechnologyResponseMapper;
import com.bootcamp.onclass.domain.api.ICapacityServicePort;
import com.bootcamp.onclass.domain.api.ITechnologyServicePort;
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
    @Test
    @DisplayName("Test successful adding  of a capacity")
    void addCapacity() {

        //GIVEN

        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new Technology(2L, "Node.js", "Entorno para construir servidores escalables en JavaScript"));
        technologies.add(new Technology(3L, "Spring Boot", "Framework Java para desarrollo rápido de aplicaciones"));

        Capacity capacity = new Capacity(1L,
                "Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies);

        AddCapacityRequest request = new AddCapacityRequest("Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies);

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

        int page = 0;
        int size = 10;
        boolean orderAsc = true;
        boolean orderName = true;

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


        // WHEN

        when(capacityServicePort.getAllCapacities(page, size, orderAsc, orderName)).thenReturn(capacities);
        controller.getAllCapacities(page, size, orderAsc, orderName);

        // THEN

        verify(capacityServicePort).getAllCapacities(page, size, orderAsc, orderName);
        verify(capacityResponseMapper, times(capacities.size())).modelToFindResponse(any());
    }

}