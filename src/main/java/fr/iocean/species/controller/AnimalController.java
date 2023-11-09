package fr.iocean.species.controller;

import fr.iocean.species.model.Animal;
import fr.iocean.species.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class AnimalController {
    @Autowired
    private AnimalRepository animalRepository;

    /**
     * @param model
     * @return
     */
    @GetMapping("/animal")
    public String listAllAnimal(Model model) {
        model.addAttribute("listSpecies", animalRepository.findAll());
        return "list_animal";
    }

    /**
     * Retourner une vue qui va afficher un animal donné en fonction de l'id en paramètre
     * afin qu'on puisse la modifier via un formulaire sur le template
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/animal/{id}")
    public String getOneAnimal(@PathVariable("id") Integer id, Model model) {
        Optional<Animal> animalToSearch = animalRepository.findById(id);
        model.addAttribute(animalToSearch.get());
        return "update_species";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/animal/create")
    public String getCreateAnimalTemplate(Model model) {
        model.addAttribute(new Animal());
        return "create_animal";
    }
}
