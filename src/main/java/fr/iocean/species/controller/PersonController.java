package fr.iocean.species.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import fr.iocean.species.model.Person;
import fr.iocean.species.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public void createPerson(@RequestBody @Valid Person personToCreate) {
        if(personToCreate.getId() != null) {
            System.out.println("Id non renseigné " + HttpStatus.BAD_REQUEST);
        }
         else {
             this.personService.create(personToCreate);
        }
    }
    @PutMapping
    public void updatePerson(@RequestBody @Valid Person updatedPerson) {
        if(updatedPerson.getId() != null) {
            System.out.println("Id non renseigné " + HttpStatus.BAD_REQUEST);
        } else {
            this.personService.update(updatedPerson);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Integer id) {
        final Person person = personService.findById(id);
        personService.delete(person);
    }

    @GetMapping("/pages")
    public Page<Person> findAllPages(Pageable pageable) {
        return personService.findAllPages(pageable);
    }
}
