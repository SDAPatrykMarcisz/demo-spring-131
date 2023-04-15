package pl.marcisz.patryk.demo.spring131.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pl.marcisz.patryk.demo.spring131.model.dto.Translation;
import pl.marcisz.patryk.demo.spring131.service.TranslationService;

import java.util.List;

@RestController //spring wola za nas new TranslationController() i zarzadza obiektem tej klasy
public class TranslationController {

    private final TranslationService translationService;

    @Autowired
    public TranslationController(
            @Qualifier("translationServiceImpl") TranslationService translationService) {
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
    public void createTranslation(@RequestBody Translation newTranslation){
        System.out.println(newTranslation);
        System.out.println(newTranslation.getCode());
        System.out.println(newTranslation.getTranslations());
        //translationService.saveTranslation(newTranslation);
    }
//    @RequestMapping(method = RequestMethod.POST, path = "/translations")
//    public void createTranslation(@RequestBody String newTranslation){
//        System.out.println(newTranslation);
//        //translationService.saveTranslation(newTranslation);
//    }

}
