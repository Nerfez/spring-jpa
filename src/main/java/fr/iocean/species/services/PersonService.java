package fr.iocean.species.services;

import fr.iocean.species.model.Person;
import fr.iocean.species.repository.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return this.personRepository.findAll();
    }
    public Person findById(Integer id) {
        return this.personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Person create(@Valid Person personToCreate) {
        return this.personRepository.save(personToCreate);
    }

    public Person update(@Valid Person updatedPerson) {
        return this.personRepository.save(updatedPerson);
    }

    public void delete(@Valid Person deletedPerson) {
        this.personRepository.delete(deletedPerson);
    }
}
