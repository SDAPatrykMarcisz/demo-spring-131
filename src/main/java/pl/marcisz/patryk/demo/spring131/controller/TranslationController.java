package pl.marcisz.patryk.demo.spring131.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pl.marcisz.patryk.demo.spring131.exception.CustomException;
import pl.marcisz.patryk.demo.spring131.model.dto.ExceptionResponse;
import pl.marcisz.patryk.demo.spring131.model.dto.Translation;
import pl.marcisz.patryk.demo.spring131.model.dto.TranslationUpdate;
import pl.marcisz.patryk.demo.spring131.service.TranslationService;

import java.util.List;

@RestController //spring wola za nas new TranslationController() i zarzadza obiektem tej klasy
//@RequestMapping(path = "/api") //wszystkie endpointy musza teraz sie zaczynac od /api (konkatenacja sciezki)
public class TranslationController {

    private final TranslationService translationService;

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleValidationError(MethodArgumentNotValidException argumentNotValidException){
        StringBuilder errorMessage = new StringBuilder("Błędy podczas walidacji: ");

        for (FieldError fieldError : argumentNotValidException.getBindingResult().getFieldErrors()) {
            errorMessage.append(fieldError.getField()).append(" ").append(fieldError.getDefaultMessage()).append(" | ");
        }
        return new ExceptionResponse(errorMessage.toString());
    }


    @Autowired
    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @RequestMapping(path = "/hello")
    public String hello(){
        return "hello";
    }

    //CRUD - R = READ / GET
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, path = "/translations")
    @GetMapping(path = "/translations")
    public List<Translation> getAllTranslations() {
        return translationService
                .getAllTranslationsFromDataSource();
    }

    //CRUD - C = CREATE / POST
    @RequestMapping(method = RequestMethod.POST, path = "/translations")
    public ResponseEntity<Void> createTranslation(@RequestBody @Valid Translation newTranslation) {
        List<Long> ids = translationService.saveTranslation(newTranslation).stream().map(x -> x.getId()).toList();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("x-created-object-id", ids.toString())
                .build();
    }

    //CRUD - U = UPDATE / PUT
    @RequestMapping(method = RequestMethod.PUT, path = "/translations/{code}/{language}")
    public void updateTranslation(@PathVariable String code, @PathVariable String language, @RequestBody TranslationUpdate translationUpdate) {
        translationService.updateTranslation(code, language, translationUpdate);
    }

    //CRUD - D = DELETE
    @RequestMapping(method = RequestMethod.DELETE, path = "/translations/{code}/{language}")
    public void deleteTranslation(@PathVariable String code, @PathVariable String language) {
        translationService.deleteTranslation(code, language);
    }


//    @RequestMapping(method = RequestMethod.POST, path = "/translations")
//    public void createTranslation(@RequestBody String newTranslation){
//        System.out.println(newTranslation);
//        //translationService.saveTranslation(newTranslation);
//    }

}
