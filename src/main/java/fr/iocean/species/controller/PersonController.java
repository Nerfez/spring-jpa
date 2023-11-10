package fr.iocean.species.controller;

import fr.iocean.species.model.Person;
import fr.iocean.species.repository.PersonRepository;
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
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    /**
     * Récupère ma liste des personnes
     *
     * @param model
     * @return
     */
    @GetMapping("/person")
    public String listAllPerson(Model model) {
        model.addAttribute("listPerson", personRepository.findAll());
        return "person/list_person";
    }

    /**
     * Récupère la personne reçu
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/person/{id}")
    public String getOnePerson(@PathVariable("id") Integer id, Model model) {
        Optional<Person> personToSearch = personRepository.findById(id);
        model.addAttribute(personToSearch.get());
        return "person/update_person";
    }

    /**
     * Récupère un nouvel objet personne vide (age est initialisé et instancié à 0 sinon la vue plante)
     *
     * @param model
     * @return
     */
    @GetMapping("/person/create")
    public String getCreatePersonTemplate(Model model) {
        Person newPerson = new Person();
        newPerson.setAge(0);
        model.addAttribute("person", newPerson);
        return "person/create_person";
    }

    /**
     * récupère 5 nouvelles personnes générées aléatoirement
     *
     * @param model
     * @return
     */
    @GetMapping("/person/generate")
    public String getGeneratePersonTemplate(Model model) {
        personRepository.generateRandomEntities(5);
        return "redirect:/person";
    }

    /**
     * créer / modifie la personne
     *
     * @param personItem
     * @param bindingResult
     * @return
     */
    @PostMapping("/person")
    public String createOrUpdate(@ModelAttribute @Valid Person personItem, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return personItem.getId() == 0 ? "person/create_person" : "person/update_person";
        }
        this.personRepository.save(personItem);
        return "redirect:/person";
    }

    /**
     * supprime la personne récupérée
     *
     * @param personId
     * @return
     */
    @GetMapping("/person/delete/{id}")
    public String delete(@PathVariable("id") Integer personId) {
        Optional<Person> personIdToDelete = this.personRepository.findById(personId);
        personIdToDelete.ifPresent(person -> this.personRepository.delete(person));
        return "redirect:/person";
    }
}
