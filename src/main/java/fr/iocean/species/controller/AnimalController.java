package fr.iocean.species.controller;

import fr.iocean.species.model.Animal;
import fr.iocean.species.repository.AnimalRepository;
import fr.iocean.species.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AnimalController {
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

    /**
     * @param model
     * @return
     */
    @GetMapping("/animal")
    public String listAllAnimal(Model model) {
        model.addAttribute("listAnimal", animalRepository.findAll());
        return "animal/list_animal";
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
        model.addAttribute("listSpecies", speciesRepository.findAll());
        Optional<Animal> animalToSearch = animalRepository.findById(id);
        model.addAttribute("animal", animalToSearch.orElse(new Animal()));
        return "animal/update_animal";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/animal/create")
    public String getCreateAnimalTemplate(Model model) {
        model.addAttribute("listSpecies", speciesRepository.findAll());
        model.addAttribute("animal", new Animal());
        return "animal/create_animal";
    }

    @PostMapping("/animal")
    public String createOrUpdate(@ModelAttribute Animal animalItem) {
        this.animalRepository.save(animalItem);
        return "redirect:/animal";
    }

    @GetMapping("/animal/delete/{id}")
    public String delete(@PathVariable("id") Integer animalId) {
        Optional<Animal> animalToDelete = this.animalRepository.findById(animalId);
        animalToDelete.ifPresent(animal -> this.animalRepository.delete(animal));
        return "redirect:/animal";
    }
}
