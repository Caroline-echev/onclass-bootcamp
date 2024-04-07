package com.bootcamp.onclass.data;

import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.bootcamp.onclass.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.bootcamp.onclass.adapters.driving.http.dto.request.AddBootcampRequest;
import com.bootcamp.onclass.adapters.driving.http.dto.response.bootcamp.BootcampNameResponse;
import com.bootcamp.onclass.domain.model.Bootcamp;
import com.bootcamp.onclass.domain.model.Capacity;
import com.bootcamp.onclass.domain.model.Technology;

import java.util.ArrayList;
import java.util.List;

public class BootcampData {
    private static CapacityData capacityData = new CapacityData();


    public static Bootcamp createBootcamp() {

        List<Capacity> capacities = capacityData.createCapacities();
        return new Bootcamp(1L, "Desarrollo Full Stack",
                "Conviértete en un desarrollador versátil capaz de crear tanto la lógica del servidor como las interfaces de usuario interactivas",
                capacities);
    }

    public static Bootcamp createBootcampDuplicateCapacities() {

        List<Capacity> capacities = capacityData.createCapacitiesDuplicateCapacities();

        return new Bootcamp(1L, "Desarrollo Full Stack",
                "Conviértete en un desarrollador versátil capaz de crear tanto la lógica del servidor como las interfaces de usuario interactivas",
                capacities);
    }
    public  static  BootcampEntity createBootcampEntity() {
        List<CapacityEntity> capacityEntities = capacityData.createCapacityEntities();
        BootcampEntity bootcampEntity = new  BootcampEntity(1L, "Ciencia de Datos",
                "Domina el procesamiento de datos y el aprendizaje automático con tecnologías como Apache Spark, TensorFlow y Scikit-learn",
                capacityEntities);
        return bootcampEntity;
    }

    public  static  List<BootcampEntity> createBootcampEntities() {
        List<CapacityEntity> capacityEntities = capacityData.createCapacityEntities();
        List<BootcampEntity> bootcampEntities = new ArrayList<>();
        bootcampEntities.add(new BootcampEntity(1L, "Ciencia de Datos",
                "Domina el procesamiento de datos y el aprendizaje automático con tecnologías como Apache Spark, TensorFlow y Scikit-learn",
                capacityEntities));
        bootcampEntities.add(new BootcampEntity(2L, "Full Stack",
                "Conviértete en un desarrollador versátil capaz de crear tanto la lógica del servidor como las interfaces de usuario interactivas",
                capacityEntities));
        return bootcampEntities;
    }

    public  static    List<Bootcamp> createBootcamps() {
        List<Capacity> capacities = capacityData.createCapacities();
        List<Bootcamp> bootcamps = new ArrayList<>();
        bootcamps.add(new Bootcamp(1L, "Ciencia de Datos",
                "Domina el procesamiento de datos y el aprendizaje automático con tecnologías como Apache Spark, TensorFlow y Scikit-learn",
                capacities));
        bootcamps.add(new Bootcamp(2L, "Full Stack",
                "Conviértete en un desarrollador versátil capaz de crear tanto la lógica del servidor como las interfaces de usuario interactivas",
                capacities));
        return bootcamps;
    }
    public static AddBootcampRequest createAddBootcampRequest() {
        List<Capacity> capacities = capacityData.createCapacities();
        return new AddBootcampRequest("Desarrollo Full Stack",
                "Conviértete en un desarrollador versátil capaz de crear tanto la lógica del servidor como las interfaces de usuario interactivas",
                capacities);
    }
    public  static BootcampNameResponse createBootcampNameResponse() {
        return new BootcampNameResponse("Desarrollo Full Stack");
    }
}