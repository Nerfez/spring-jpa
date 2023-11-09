package fr.iocean.species.controller;

import fr.iocean.species.model.Person;
import fr.iocean.species.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        return "list_person";
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
        model.addAttribute(personToSearch);
        return "update_person";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/person/create")
    public String getCreatePersonTemplate(Model model) {
        model.addAttribute(new Person());
        return "create_person";
    }
}
