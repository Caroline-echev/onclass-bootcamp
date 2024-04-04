package com.bootcamp.onclass.domain.model;


public class Version {
    private  final Long id;
    private String initialDate;

    private String finalDate;
    private final  int maxCapacity;
    private Bootcamp bootcamp;
    public Version(Long id, String initialDate, String finalDate, int maxCapacity, Bootcamp bootcamp) {
        this.id = id;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.maxCapacity = maxCapacity;
        this.bootcamp = bootcamp;
    }
    public Long getId() {
        return id;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Bootcamp getBootcamp() {
        return bootcamp;
    }



    public void setBootcamp(Bootcamp bootcamp) {
        this.bootcamp = bootcamp;
    }
}
