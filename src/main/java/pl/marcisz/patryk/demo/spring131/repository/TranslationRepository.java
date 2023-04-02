package pl.marcisz.patryk.demo.spring131.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.marcisz.patryk.demo.spring131.model.entity.TranslationEntity;

@Repository
public interface TranslationRepository extends JpaRepository<TranslationEntity, Long> {
}
