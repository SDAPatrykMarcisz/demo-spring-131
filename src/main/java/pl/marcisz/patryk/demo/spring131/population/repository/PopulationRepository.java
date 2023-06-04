package pl.marcisz.patryk.demo.spring131.population.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.marcisz.patryk.demo.spring131.population.model.dao.PopulationEntity;

import java.util.Optional;

public interface PopulationRepository extends JpaRepository<PopulationEntity, Long> {
    Optional<PopulationEntity> findByName(String name);

    Iterable<? extends PopulationEntity> findAllByName(String name);

    Optional<PopulationEntity> findByNameAndPopulation(String name, long population);
}
