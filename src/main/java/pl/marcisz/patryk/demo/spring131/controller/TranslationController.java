package pl.marcisz.patryk.demo.spring131.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import pl.marcisz.patryk.demo.spring131.model.dto.Translation;
import pl.marcisz.patryk.demo.spring131.model.entity.TranslationEntity;
import pl.marcisz.patryk.demo.spring131.service.TranslationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController //spring wola za nas new TranslationController() i zarzadza obiektem tej klasy
public class TranslationController {

    private final TranslationService translationService;

    @Autowired
    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    //CRUD - R = READ / GET
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, path = "/translations")
    public List<Translation> getAllTranslations(){
        return translationService
                .getAllTranslationsFromDataSource();
    }

    //CRUD - C = CREATE / POST
    @RequestMapping(method = RequestMethod.POST, path = "/translations")
    public ResponseEntity<Void> createTranslation(@RequestBody Translation newTranslation){
        List<Long> ids = translationService.saveTranslation(newTranslation).stream().map(x -> x.getId()).toList();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("x-created-object-id", ids.toString())
                .build();
    }


//    @RequestMapping(method = RequestMethod.POST, path = "/translations")
//    public void createTranslation(@RequestBody String newTranslation){
//        System.out.println(newTranslation);
//        //translationService.saveTranslation(newTranslation);
//    }

}
