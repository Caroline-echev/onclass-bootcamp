package com.bootcamp.onclass.domain.model;


public class Technology {

    private  Long id;
    private   String name;
    private   String description;



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


}
