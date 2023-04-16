package pl.marcisz.patryk.demo.spring131.dataprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pl.marcisz.patryk.demo.spring131.model.dto.Translation;
import pl.marcisz.patryk.demo.spring131.model.entity.TranslationEntity;
import pl.marcisz.patryk.demo.spring131.repository.TranslationRepository;

import java.util.Map;

@Component
@Profile("init-data")
public class InitialDataProvider implements CommandLineRunner {

    private final TranslationRepository repository;

    @Value("${data.user.admin.login}")
    private String adminUser;

    private String adminPassword;

    @Autowired
    public InitialDataProvider(TranslationRepository repository,
                               @Value("${data.user.admin.password:haslo123}") String adminPassword, AdminDefaultData defaultData) {
        this.repository = repository;
        System.out.println(adminUser + " : " + adminPassword);
        this.adminPassword = adminPassword;
        System.out.println(defaultData.getPassword() + " : " +defaultData.getPassword());
    }

    @Override
    public void run(String... args) throws Exception {

        createAndSave("main.page.welcome-message", "EN",  "Hello");
        createAndSave("main.page.welcome-message", "PL",  "Witaj");
        createAndSave("main.page.welcome-message", "CN",  "欢迎");
        System.out.println(adminUser + " : " + adminPassword);
    }

    private void createAndSave(String code, String language, String translation) {
        TranslationEntity entity = new TranslationEntity();
        entity.setCode(code);
        entity.setLanguage(language);
        entity.setTranslation(translation);
        repository.save(entity);
    }

}
