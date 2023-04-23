package pl.marcisz.patryk.demo.spring131.population.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.marcisz.patryk.demo.spring131.population.repository.PopulationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest  //ta adnotacja mowi ze caly spring ma zostac uruchomiony w ramach testu
@AutoConfigureMockMvc //ta adnotacja udostepnia nam specjalny obiekt do testowania controllerow (jest to de facto klient http)
class PopulationControllerFullIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PopulationRepository populationRepository;

    @Value("classpath:population/valid-create-population-request.json")
    private Resource validCreatePopulationRequestBody;

    @Test
    void shouldSavePopulationToDatabaseAsNewObjectAndReturn201_Created() throws Exception {
        long countBefore = populationRepository.count();
        assertEquals(3, countBefore); //dlaczego w bazie znajduja sie 3 obiekty ? dlatego ze zostaly tam wrzucone w ramach komponentu zawierajacego CommandLineRunner

        mockMvc.perform(post("/populations")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\": \"niemcy\"}") //mozna na podstawie dostarczonego bezposrednio jsona
                        .content(validCreatePopulationRequestBody.getContentAsByteArray()) //mozna na podstawie obiektu pobranego ze sciezki, sciezka w @Value
                )
                        .andExpect(status().isCreated());

        long countAfter = populationRepository.count();
        assertEquals(countBefore + 1, countAfter);
    }

    @Test
    void shouldReturnValidationErrorWhenNameNotPrented() throws Exception {
        mockMvc.perform(post("/populations")
                                .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                )
                .andExpect(jsonPath("$.errorMessage").value("Błędy podczas walidacji: name must not be empty | "))
                .andExpect(status().isBadRequest());
    }

}
