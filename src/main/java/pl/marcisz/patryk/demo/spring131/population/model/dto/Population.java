package pl.marcisz.patryk.demo.spring131.population.model.dto;

import jakarta.validation.constraints.NotEmpty;

public class Population {
    @NotEmpty
    private String name;
    private int population;
    private int increase;
    private int id;

    public Population(String name, int population, int increase, int id) {
        this.name = name;
        this.population = population;
        this.increase = increase;
        this.id = id;
    }

    public Population() {
    }

    public Population(String name, int population, int increase) {
    }

    public String getName() {
        return name;
    }

    public int population() {
        return population;
    }

    public int getIncrease() {
        return increase;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

    public int getPopulation() {
        return population;
    }
}
