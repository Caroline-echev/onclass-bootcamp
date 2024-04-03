package com.bootcamp.onclass.data;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import com.bootcamp.onclass.adapters.driving.http.dto.request.AddTechnologyRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.technology.TechnologyResponse;
import com.bootcamp.onclass.domain.model.Technology;

import java.util.ArrayList;
import java.util.List;

public class TechnologyData {
    public static final String TECHNOLOGY_NAME = "Python";
    public static String NOT_EXISTING_TECHNOLOGY = "NotExistingTech";
    public static Technology createTechnology() {

        return new Technology(1L, "Python","Biblioteca para interfaces de usuario dinámicas.");

    }

    public static TechnologyEntity createTechnologyEntity() {

        return new TechnologyEntity(1L, "Python","Biblioteca para interfaces de usuario dinámicas.", null);

    }
    public static List<Technology> createTechnologies() {

        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new Technology(2L, "Node.js", "Entorno para construir servidores escalables en JavaScript"));
        technologies.add(new Technology(3L, "Spring Boot", "Framework Java para desarrollo rápido de aplicaciones"));
        return technologies;
    }
    public static List<Technology> createTechnologiesDuplicate() {

        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new Technology(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new Technology(3L, "Spring Boot", "Framework Java para desarrollo rápido de aplicaciones"));
        return technologies;
    }
    public static AddTechnologyRequest createAddTechnologyRequest() {

        return new AddTechnologyRequest("Python","Biblioteca para interfaces de usuario dinámicas.");
    }

    public static List<TechnologyResponse> createTechnologyResponse() {

        List<TechnologyResponse> technologies = new ArrayList<>();
        technologies.add(new TechnologyResponse(1L, "Java", "Lenguaje robusto para desarrollo backend"));
        technologies.add(new TechnologyResponse(2L, "Node.js", "Entorno para construir servidores escalables en JavaScript"));
        technologies.add(new TechnologyResponse(3L, "Spring Boot", "Framework Java para creación de aplicaciones"));
        return technologies;
    }
    public static List<TechnologyEntity> createTechnologyEntities() {

        List<TechnologyEntity> technologyEntities = new ArrayList<>();
        technologyEntities.add(new TechnologyEntity(1L, "Java", "Programming language",null));
        technologyEntities.add(new TechnologyEntity(2L, "Python", "Scripting language", null));
        technologyEntities.add(new TechnologyEntity(3L, "JavaScript", "Programming language", null));
        return technologyEntities;
    }
}
