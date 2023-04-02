package pl.marcisz.patryk.demo.spring131;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslationController {

    @RequestMapping(method = RequestMethod.GET, path = "/translations")
    public void getAllTranslations(){
        System.out.println("Hello");
    }

//    @RequestMapping(method = RequestMethod.POST, path = "/translations")
//    public void saveNewTranslation(){
//        System.out.println("Hello");
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, path = "/translations")
//    public void updateTranslation(){
//        System.out.println("Hello");
//    }

}
