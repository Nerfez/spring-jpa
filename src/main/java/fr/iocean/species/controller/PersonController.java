package fr.iocean.species.controller;

import fr.iocean.species.dto.PersonDto;
import fr.iocean.species.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public List<PersonDto> findAll(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") Integer id) {
        return personService.findById(id);
    }

    @PostMapping
    public String createPerson(@RequestBody @Valid Person personToCreate) {
        if(personToCreate.getId() != null) {
            this.personService.create(personToCreate);
            return ("Personne créée " + HttpStatus.OK);
        }
         else {
            return ("Id non renseigné " + HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public String updatePerson(@RequestBody @Valid Person updatedPerson) {
        if(updatedPerson.getId() != null) {
            this.personService.update(updatedPerson);
            return ("Personne modifiée " + HttpStatus.OK);
        } else {
            return ("Id non renseigné " + HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Integer id) {
        final Person person = personService.findById(id);
        personService.delete(person);
    }

    @GetMapping("/pages")
    public Page<Person> findAllPages(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "20") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return personService.findAllPages(pageable);
    }
}
