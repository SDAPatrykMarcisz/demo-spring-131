package pl.marcisz.patryk.demo.spring131.population.service;

import org.springframework.stereotype.Service;
import pl.marcisz.patryk.demo.spring131.population.model.dto.Population;
import pl.marcisz.patryk.demo.spring131.population.model.dto.PopulationUpdate;
import pl.marcisz.patryk.demo.spring131.population.model.dao.PopulationEntity;
import pl.marcisz.patryk.demo.spring131.population.repository.PopulationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PopulationService {
    private final PopulationRepository populationRepository;

    public PopulationService(PopulationRepository populationRepository) {
        this.populationRepository = populationRepository;
    }

    public List<Population> getAllPopulations() {
        List<PopulationEntity> all = populationRepository.findAll();
        List<Population> selected = new ArrayList<>();
        for (PopulationEntity populationEntity : all) {
            Population population = new Population();
            population.setName(populationEntity.getName());
            population.setPopulation((int) populationEntity.getPopulation());
            population.setIncrease((int) populationEntity.getIncrease());
            selected.add(population);
        }
        return selected;
    }

    public void createOrUpdate(Population population) {
        try {
            PopulationUpdate toUpdate = new PopulationUpdate();
            toUpdate.setPopulation(toUpdate.getPopulation());
            toUpdate.setIncrease(toUpdate.getIncrease());
            updatePopulation(population.getName(), toUpdate);
        } catch (IllegalArgumentException updateFailed) {
            savePopulation(population);
        }
    }

    public PopulationEntity savePopulation(Population newPopulation) {
        PopulationEntity entity = new PopulationEntity();
        entity.setName(newPopulation.getName());
        entity.setIncrease(newPopulation.getIncrease());
        entity.setPopulation(newPopulation.getPopulation());
        return populationRepository.save(entity);
    }

    public void updatePopulation(String name, PopulationUpdate populationUpdate) {
        populationRepository.findByName(name)
                .map(x -> {
                    x.setPopulation(Long.parseLong(populationUpdate.getPopulation()));
                    x.setIncrease(Integer.parseInt(populationUpdate.getIncrease()));
                    return x;
                })
                .ifPresentOrElse(
                        populationRepository::save,
                        () -> {
                            throw new IllegalArgumentException("nie znaleziono obiektu dla podanych name i population");
                        }
                );
    }

    public void deletePopulation(String name) {
        populationRepository.deleteAll(populationRepository.findAllByName(name));
    }

    public void deleteTranslation(String name) {
        populationRepository.findByName(name)
                .ifPresent(populationRepository::delete);
//        translationRepository.delete(translationRepository.findByCodeAndLanguage(code, language).get()); //ale get rzuci NPE jak nie ma takiego wpisu
    }

    public Population getPopulationByName(String populationName) {
        Optional<PopulationEntity> fromDb = populationRepository.findByName(populationName);
        return fromDb
                .map(x -> {
                    Population population = new Population();
                    population.setName(x.getName());
                    population.setPopulation((int) x.getPopulation());
                    population.setIncrease((int) x.getIncrease());
                    return population;
                })
                .orElseThrow();
    }
}
