package fr.iocean.species.controller;

import fr.iocean.species.model.Species;
import fr.iocean.species.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class SpeciesController {

    @Autowired
    private SpeciesRepository speciesRepository;

    /**
     * @param model
     * @return
     */
    @GetMapping("/species")
    public String listAllSpecies(Model model) {
    model.addAttribute("listSpecies", speciesRepository.findAll());
    return "list_species";
    }

    /**
     * Retourner une vue qui va afficher une species donnée en fonction de l'id en paramètre
     * afin qu'on puisse la modifier via un formulaire sur le template
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/species/{id}")
    public String getOneSpecies(@PathVariable("id") Integer id, Model model) {
        Optional<Species> speciesToSearch = speciesRepository.findById(id);
        model.addAttribute(speciesToSearch);
        return "update_species";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/species/create")
    public String getCreateSpeciesTemplate(Model model) {
        model.addAttribute(new Species());
        return "create_species";
    }
}
