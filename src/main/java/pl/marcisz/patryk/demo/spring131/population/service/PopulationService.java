package pl.marcisz.patryk.demo.spring131.population.service;

import org.springframework.stereotype.Service;
import pl.marcisz.patryk.demo.spring131.population.model.dto.Population;
import pl.marcisz.patryk.demo.spring131.population.model.dto.PopulationUpdate;
import pl.marcisz.patryk.demo.spring131.population.model.dao.PopulationEntity;
import pl.marcisz.patryk.demo.spring131.population.repository.PopulationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopulationService {
    private final PopulationRepository populationRepository;
    public PopulationService(PopulationRepository populationRepository) {
        this.populationRepository = populationRepository;
    }
    public List<Population> getAllPopulations() {
        List<PopulationEntity> all = populationRepository.findAll();
        List <Population> selected = new ArrayList<>();
        for (PopulationEntity populationEntity : all){
            Population population = new Population();
            population.setName(populationEntity.getName());
            population.setPopulation((int) populationEntity.getPopulation());
            population.setIncrease((int) populationEntity.getIncrease());
            selected.add(population);
        }
        return selected;
    }
    public PopulationEntity savePopulation(Population newPopulation) {
        PopulationEntity entity = new PopulationEntity();
        entity.setName(newPopulation.getName());
        entity.setIncrease(newPopulation.getIncrease());
        entity.setPopulation(newPopulation.getPopulation());
        return populationRepository.save(entity);
    }
    public void updatePopulation(String name, long population, double increase, PopulationUpdate populationUpdate) {
        populationRepository.findByNameAndPopulation(name, population)
                .map(x -> {
                    x.setPopulation(Long.parseLong(populationUpdate.getPopulation()));
                    return x;
                })
                .ifPresentOrElse(
                        populationRepository::save,
                        () -> {
                            throw new IllegalArgumentException("nie znaleziono obiektu dla podanych name i population");
                        }
                );
    }
    public void deletePopulation(String name){
        populationRepository.deleteAll(populationRepository.findAllByName(name));
    }
    public void deleteTranslation(String name, long population){
        populationRepository.findByNameAndPopulation(name, population)
                .ifPresent(populationRepository::delete);
//        translationRepository.delete(translationRepository.findByCodeAndLanguage(code, language).get()); //ale get rzuci NPE jak nie ma takiego wpisu
    }
}
