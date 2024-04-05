package com.bootcamp.onclass.domain.model;


import java.time.LocalDate;

public class Version {
    private  final Long id;
    private final LocalDate initialDate;

    private final LocalDate finalDate;
    private final  int maxCapacity;
    private Bootcamp bootcamp;
    public Version(Long id, LocalDate initialDate, LocalDate finalDate, int maxCapacity, Bootcamp bootcamp) {
        this.id = id;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.maxCapacity = maxCapacity;
        this.bootcamp = bootcamp;
    }
    public Long getId() {
        return id;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public LocalDate getFinalDate() {
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
