package pl.marcisz.patryk.demo.spring131.dataprovider;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Data
public class PopulationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kraj;
    private long liczebnosc;
    private double przyrost;
}
