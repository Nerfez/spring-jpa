package fr.iocean.species.controller;

import fr.iocean.species.model.Animal;
import fr.iocean.species.model.Person;
import fr.iocean.species.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    public List<Person> findAll(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") Integer id) {
        return personService.findById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody @Valid Person personToCreate) {
        return this.personService.create(personToCreate);
    }
    @PutMapping
    public Person updatePerson(@RequestBody @Valid Person updatedPerson) {
        return this.personService.update(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Integer id) {
        final Person person = personService.findById(id);
        personService.delete(person);
    }
}
