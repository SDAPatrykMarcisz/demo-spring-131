package pl.marcisz.patryk.demo.spring131.population.service;

import org.junit.jupiter.api.Test;
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

class PopulationServiceTest {

    @Test
    void updatePopulation() {
        //given
        String name = "polska";
        long population = 1000;
        double increase = 10.0;
        PopulationUpdate populationUpdate = new PopulationUpdate();
        populationUpdate.setPopulation("2000");

        PopulationEntity expectedRepositoryObject = new PopulationEntity();

        //when
        PopulationService populationService = new PopulationService(new PopulationRepository() {
            @Override
            public Optional<PopulationEntity> findByNameAndPopulation(String name, long population) {
                if(name.equals("polska") && population == 1000){
                    return Optional.of(expectedRepositoryObject);
                }
                return Optional.empty();
            }

            @Override
            public Iterable<? extends PopulationEntity> findAllByName(String name) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends PopulationEntity> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends PopulationEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<PopulationEntity> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public PopulationEntity getOne(Long aLong) {
                return null;
            }

            @Override
            public PopulationEntity getById(Long aLong) {
                return null;
            }

            @Override
            public PopulationEntity getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends PopulationEntity> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends PopulationEntity> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public <S extends PopulationEntity> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public List<PopulationEntity> findAll() {
                return null;
            }

            @Override
            public List<PopulationEntity> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends PopulationEntity> S save(S entity) {
                System.out.println("save called");
                return null;
            }

            @Override
            public Optional<PopulationEntity> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(PopulationEntity entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends PopulationEntity> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public List<PopulationEntity> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<PopulationEntity> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends PopulationEntity> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends PopulationEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends PopulationEntity> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends PopulationEntity> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends PopulationEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        });
        populationService.updatePopulation(name, population, increase, populationUpdate);

        //then





    }
}
