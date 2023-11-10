package fr.iocean.species.controller;

import fr.iocean.species.model.Animal;
import fr.iocean.species.repository.AnimalRepository;
import fr.iocean.species.repository.SpeciesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
     * recupere la liste des animaux
     * @param model
     * @return
     */
    @GetMapping("/animal")
    public String listAllAnimal(Model model) {
        model.addAttribute("listAnimal", animalRepository.findAll());
        return "animal/list_animal";
    }

    /**
     * recupere l animal
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
     * creer un nouvel animal ET une liste des especes
     * @param model
     * @return
     */
    @GetMapping("/animal/create")
    public String getCreateAnimalTemplate(Model model) {
        model.addAttribute("listSpecies", speciesRepository.findAll());
        model.addAttribute("animal", new Animal());
        return "animal/create_animal";
    }

    /**
     * creer ou modifie l animal recupere
     *
     * @param animalItem
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping("/animal")
    public String createOrUpdate(@ModelAttribute @Valid Animal animalItem, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("listSpecies", speciesRepository.findAll());
            return animalItem.getId() == 0 ? "animal/create_animal" : "animal/update_animal";
        }
        this.animalRepository.save(animalItem);
        return "redirect:/animal";
    }

    /**
     * supprime l animal recupere
     *
     * @param animalId
     * @return
     */
    @GetMapping("/animal/delete/{id}")
    public String delete(@PathVariable("id") Integer animalId) {
        Optional<Animal> animalToDelete = this.animalRepository.findById(animalId);
        animalToDelete.ifPresent(animal -> this.animalRepository.delete(animal));
        return "redirect:/animal";
    }
}
