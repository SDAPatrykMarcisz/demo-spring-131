package pl.marcisz.patryk.demo.spring131.population.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.marcisz.patryk.demo.spring131.population.model.dto.Population;
import pl.marcisz.patryk.demo.spring131.population.service.PopulationService;
import pl.marcisz.patryk.demo.spring131.population.model.dto.PopulationUpdate;

import java.util.List;

@RestController
public class PopulationController {
    private PopulationService populationService;

    @Autowired
    public PopulationController(PopulationService populationService) {
        this.populationService = populationService;
    }

    //CRUD - R = READ / GET
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, path = "/populations")
    public List<Population> getAllPopulations() {
        return populationService.getAllPopulations();
    }

    //CRUD - C = CREATE / POST
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, path = "/populations")
    public void createPopulation(@RequestBody @Valid Population newPopulation) {
        // System.out.println(newPopulation.getId());
        System.out.println(newPopulation.getName());
        System.out.println(newPopulation.getPopulation());
        System.out.println(newPopulation.getIncrease());
        populationService.savePopulation(newPopulation);
    }

    //CRUD - U = UPDATE / PUT
    @RequestMapping(method = RequestMethod.PUT, path = "/populations/{name}/{population}")
    public void updatePopulation(@PathVariable String name, @PathVariable long population, @PathVariable double increase, @RequestBody PopulationUpdate populationUpdate) {
        populationService.updatePopulation(name, population, increase, populationUpdate);
    }
    // /populations/name/population --> String name => name, long population => blad, bo population nie mozna przerobic na longa
    // /population/Chiny/20 --> String name => Chiny, long population => 20
}
