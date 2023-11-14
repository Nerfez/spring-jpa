package fr.iocean.species.services;

import fr.iocean.species.advices.ControllerExceptionHandler;
import fr.iocean.species.mappers.PersonMapper;
import fr.iocean.species.model.Person;
import fr.iocean.species.repository.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import fr.iocean.species.dto.PersonDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**public List<Person> findAll() {
        return this.personRepository.findAll();
    }**/

    public List<PersonDto> findAll() {
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
    }

    public Person findById(Integer id) {
        return this.personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Person create(@RequestBody @Valid Person personToCreate) {
        if (personToCreate.getId() == null || personToCreate.getId() == 0) {
            throw new ControllerExceptionHandler.EntityToUpdateHasAnIdException("Person ne contient pas d'id, impossible de créer");
        }
        return this.personRepository.save(personToCreate);
    }

    public Person update(@RequestBody @Valid Person updatedPerson) {
        if (updatedPerson.getId() == null || updatedPerson.getId() == 0) {
            throw new ControllerExceptionHandler.EntityToUpdateHasAnIdException("Person ne contient pas d'id, impossible de modifier");
        }
        return this.personRepository.save(updatedPerson);
    }

    public void delete(@Valid Person deletedPerson) {
        this.personRepository.delete(deletedPerson);
    }

    public Page<Person> findAllPages(Pageable pageable) {
        return this.personRepository.findAll(pageable);
    }
}
