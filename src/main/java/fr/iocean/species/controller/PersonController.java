package fr.iocean.species.controller;

import fr.iocean.species.model.Person;
import fr.iocean.species.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
     * @param model
     * @return
     */
    @GetMapping("/person")
    public String listAllPerson(Model model) {
        model.addAttribute("listPerson", personRepository.findAll());
        return "person/list_person";
    }

    /**
     * Retourner une vue qui va afficher une person donnée en fonction de l'id en paramètre
     * afin qu'on puisse la modifier via un formulaire sur le template
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
     * @param model
     * @return
     */
    @GetMapping("/person/create")
    public String getCreatePersonTemplate(Model model) {
        model.addAttribute("person", new Person());
        return "person/create_person";
    }

    @PostMapping("/person")
    public String createOrUpdate(@ModelAttribute Person personItem) {
        this.personRepository.save(personItem);
        return "redirect:/person";
    }

    @GetMapping("/person/delete/{id}")
    public String delete(@PathVariable("id") Integer personId) {
        Optional<Person> personIdToDelete = this.personRepository.findById(personId);
        personIdToDelete.ifPresent(person -> this.personRepository.delete(person));
        return "redirect:/person";
    }
}
