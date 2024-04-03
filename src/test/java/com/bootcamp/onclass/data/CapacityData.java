package com.bootcamp.onclass.data;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp.onclass.adapters.driving.http.dto.request.AddCapacityRequest;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.model.Technology;

import java.util.ArrayList;
import java.util.List;

public class CapacityData {
    private static TechnologyData technologyData = new TechnologyData();
    public static Capacity createCapacity() {

        List<Technology> technologies = technologyData.createTechnologies();
        return new Capacity(1L,
                "Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies);
    }
    public static CapacityEntity createCapacityEntity() {

        List<TechnologyEntity> technologyEntities = technologyData.createTechnologyEntities();

        return  new CapacityEntity().builder()
                .id(1L)
                .name("Desarrollador Backend")
                .description("Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación")
                .technologies(technologyEntities)
                .build();
    }
    public static Capacity createCapacityDuplicateTechnologies() {

        List<Technology> technologies = technologyData.createTechnologiesDuplicate();
        return new Capacity(1L,
                "Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies);
    }

    public static List<Capacity> createCapacities() {

        List<Technology> technologies = technologyData.createTechnologies();

        List<Technology> technologies1 = technologyData.createTechnologies();

        List<Capacity> capacities = new ArrayList<>();
        capacities.add (new  Capacity(1L,
                "Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies));
        capacities.add (new  Capacity(2L,
                "Desarrollador Frontend",
                "Creación de la interfaz de usuario y experiencia de usuario de una aplicación web o móvil",
                technologies1));

        return capacities;
    }
    public static List<Capacity> createCapacitiesDuplicateCapacities() {

        List<Technology> technologies = technologyData.createTechnologies();

        List<Technology> technologies1 = technologyData.createTechnologies();

        List<Capacity> capacities = new ArrayList<>();
        capacities.add (new  Capacity(1L,
                "Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies));
        capacities.add (new  Capacity(1L,
                "Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies));

        return capacities;
    }

    public static AddCapacityRequest createAddCapacityRequest() {

        List<Technology> technologies = technologyData.createTechnologies();
        return new AddCapacityRequest("Desarrollador Backend",
                "Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación",
                technologies);
    }

    public static List<CapacityEntity> createCapacityEntities() {

        List<Technology> technologies = technologyData.createTechnologies();

        List<TechnologyEntity> technologies1 = technologyData.createTechnologyEntities();

        List<CapacityEntity> capacityEntities = new ArrayList<>();
        capacityEntities.add(new CapacityEntity().builder()
                .id(1L)
                .name("Desarrollador Backend")
                .description("Diseño y construcción de la lógica y funcionalidades de la parte del servidor de una aplicación")
                .technologies(technologies1)
                .build());
        capacityEntities.add(new CapacityEntity().builder()
                .id(2L)
                .name("Desarrollador Frontend")
                .description("Creación de la interfaz de usuario y experiencia de usuario de una aplicación web o móvil")
                .technologies(technologies1)
                .build());

        return capacityEntities;
    }
}
