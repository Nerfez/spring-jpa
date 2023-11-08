package fr.iocean.species.controller;

import fr.iocean.species.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpeciesController {

    @Autowired
    private SpeciesRepository speciesRepository;

    @GetMapping("/species")
    public String listAllSpecies(Model model) {
    model.addAttribute(speciesRepository.findAll());
    return "list_species";
    }
}
