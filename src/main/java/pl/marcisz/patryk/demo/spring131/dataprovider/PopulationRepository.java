package pl.marcisz.patryk.demo.spring131.dataprovider;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PopulationRepository extends JpaRepository<PopulationEntity, Long> {
}
