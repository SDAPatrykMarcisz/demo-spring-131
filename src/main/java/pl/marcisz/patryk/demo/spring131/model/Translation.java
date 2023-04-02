package pl.marcisz.patryk.demo.spring131.model;

import java.util.Map;

public class Translation {

    public Translation(String code, Map<String, String> translations) {
        this.code = code;
        this.translations = translations;
    }

    private String code;
    private Map<String, String> translations;

    public String getCode() {
        return code;
    }

    public Map<String, String> getTranslations() {
        return translations;
    }
}
