package pl.marcisz.patryk.demo.spring131.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "translations")
@Entity
@Data
public class TranslationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String language;

    private String translation;


}
