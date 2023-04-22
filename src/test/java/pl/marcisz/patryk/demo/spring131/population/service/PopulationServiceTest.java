package pl.marcisz.patryk.demo.spring131.population.service;

import org.junit.jupiter.api.Test;
import pl.marcisz.patryk.demo.spring131.population.model.dto.PopulationUpdate;

import static org.junit.jupiter.api.Assertions.*;

class PopulationServiceTest {

    @Test
    void updatePopulation() {
        //given
        String name = "polska";
        long population = 1000;
        double increase = 10.0;
        PopulationUpdate populationUpdate = new PopulationUpdate();
        populationUpdate.setPopulation("2000");

        //when
        PopulationService populationService = new PopulationService(null);
        populationService.updatePopulation(name, population, increase, populationUpdate);

        //then





    }
}
