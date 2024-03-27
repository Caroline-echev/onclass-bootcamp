package com.bootcamp.onclass.domain.model;

import java.util.Set;

public class Technology {

    private  Long id;
    private   String name;
    private   String description;
    private Set<Capacity> capacities;


    public Technology(Long id, String name, String description) {

        this.id = id;

        this.name = name;

        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public Set<Capacity> getCapacities() {
        return capacities;
    }

    public void setCapacities(Set<Capacity> capacities) {
        this.capacities = capacities;
    }

}
