package pl.marcisz.patryk.demo.spring131.population.dataprovider;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.marcisz.patryk.demo.spring131.population.model.dao.PopulationEntity;
import pl.marcisz.patryk.demo.spring131.population.repository.PopulationRepository;

@Component
public class InitialDataPopulation implements CommandLineRunner {
    private PopulationRepository repository;
    public  InitialDataPopulation(PopulationRepository repository) {
        this.repository = repository;
    }
    //public InitialDataPopulation(PopulationRepository repository) {
    //this.repository = repository;
    @Override
    public void run(String... args) throws Exception {
        createAndSave1("Polska", 40, 1);
        createAndSave1("Niemcy", 30, 2);
        createAndSave1("Chiny", 20, 3);
    }
    private void createAndSave1(String name, int population, int increase) {
        PopulationEntity entity = new PopulationEntity();
        entity.setName(name);
        entity.setPopulation(population);
        entity.setIncrease(increase);
        repository.save(entity);
    }
}
