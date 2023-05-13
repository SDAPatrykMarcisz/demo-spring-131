package pl.marcisz.patryk.demo.spring131.controller.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.marcisz.patryk.demo.spring131.population.model.dto.Population;
import pl.marcisz.patryk.demo.spring131.population.service.PopulationService;

import java.util.List;

@Slf4j
@Controller
public class MvcPopulationController {
    private final PopulationService populationService;

    @Autowired
    public MvcPopulationController(PopulationService populationService) {
        this.populationService = populationService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        List<Population> allPopulations = populationService.getAllPopulations();
        log.info("pobrano {} elementow", allPopulations.size());
        model.addAttribute("populations", allPopulations);
        return "main";
    }
}
