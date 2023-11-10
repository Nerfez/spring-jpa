package fr.iocean.species.controller;

import fr.iocean.species.model.Species;
import fr.iocean.species.repository.SpeciesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        List<Species> species = speciesRepository.findAll();
        model.addAttribute("speciesList", species);
        return "species/list_species";
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
        model.addAttribute(speciesToSearch.get());
        return "species/update_species";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/species/create")
    public String getCreateSpeciesTemplate(Model model) {
        model.addAttribute("species", new Species());
        return "species/create_species";
    }

    @PostMapping("/species")
    public String createOrUpdate(@ModelAttribute @Valid Species speciesItem, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return speciesItem.getId() == 0 ? "species/create_species" : "species/update_species";
        }
        this.speciesRepository.save(speciesItem);
        return "redirect:/species";
    }

    @GetMapping("/species/delete/{id}")
    public String delete(@PathVariable("id") Integer speciesId) {
        Optional<Species> speciesToDelete = this.speciesRepository.findById(speciesId);
        speciesToDelete.ifPresent(species -> this.speciesRepository.delete(species));
        return "redirect:/species";
    }
}
