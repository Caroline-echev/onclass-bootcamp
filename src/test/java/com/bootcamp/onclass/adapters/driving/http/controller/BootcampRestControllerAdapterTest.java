package com.bootcamp.onclass.adapters.driving.http.controller;

import com.bootcamp.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.bootcamp.onclass.adapters.driving.http.mapper.IBootcampRequestMapper;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BootcampRestControllerAdapterTest {

    @Mock
    private IBootcampServicePort bootcampServicePort ;

    @Mock
    private IBootcampRequestMapper bootcampRequestMapper ;

    @InjectMocks
    private BootcampRestControllerAdapter controller;
    @Test
    @DisplayName("Test successful adding  of a bootcamp")
    void addBootcamp() {

        //GIVEN

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

        Bootcamp bootcamp = new Bootcamp(1L, "Desarrollo Full Stack",
                "Conviértete en un desarrollador versátil capaz de crear tanto la lógica del servidor como las interfaces de usuario interactivas",
                capacities);

        AddBootcampRequest  request = new AddBootcampRequest("Desarrollo Full Stack",
                "Conviértete en un desarrollador versátil capaz de crear tanto la lógica del servidor como las interfaces de usuario interactivas",
                capacities);

        when(bootcampRequestMapper.addRequestToBootcamp(request)).thenReturn(bootcamp);
        when(bootcampServicePort.addBootcamp(bootcamp)).thenReturn(bootcamp);

        // WHEN
        ResponseEntity<Bootcamp> responseEntity = controller.addBootcamp(request);

        // THEN
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bootcamp, responseEntity.getBody());
    }
}