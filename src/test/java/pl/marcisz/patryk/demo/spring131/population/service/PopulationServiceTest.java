package pl.marcisz.patryk.demo.spring131.population.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.marcisz.patryk.demo.spring131.population.model.dao.PopulationEntity;
import pl.marcisz.patryk.demo.spring131.population.model.dto.PopulationUpdate;
import pl.marcisz.patryk.demo.spring131.population.repository.PopulationRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PopulationServiceTest {

    @Mock
    private PopulationRepository populationRepository; //obiekt typu "tanczy jak mu zagram"

    private PopulationService populationService;

    @BeforeEach
    void setUp(){
        this.populationService = new PopulationService(populationRepository);
    }
    @Test
    void shouldUpdateWhenPopulationInDatabaseExists() {
        //given
        String name = "polska";
        long population = 1000;
        double increase = 10.0;
        PopulationUpdate populationUpdate = new PopulationUpdate();
        populationUpdate.setPopulation("2000");

        PopulationEntity expectedRepositoryObject = new PopulationEntity();
        Mockito.when(populationRepository.findByNameAndPopulation(name, population))
                .thenReturn(Optional.of(expectedRepositoryObject));

        //when
        populationService.updatePopulation(name, population, increase, populationUpdate);

        //then
        Mockito.verify(populationRepository).save(any());
    }
}
