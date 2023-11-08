package fr.iocean.species.repository;

import fr.iocean.species.model.Person;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void findByAgeGreaterThanEqual() {
        Person person1 = new Person(12, "Clément", "Olivier");
        Person person2 = new Person(27, "Rossi", "Oddet");
        Person person3 = new Person(67, "Mec", "Vieux");

        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);

        List<Person> result = personRepository.findByAgeGreaterThanEqual(35);

        assertEquals(1, result.size());
    }

    @Test
    void findPeopleByAgeRange() {
        Person person1 = new Person(12, "Clément", "Olivier");
        Person person2 = new Person(27, "Rossi", "Oddet");
        Person person3 = new Person(67, "Mec", "Vieux");

        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);

        List<Person> result = personRepository.findPeopleByAgeRange(10, 25);

        assertEquals(1, result.size());
        assertEquals("Clément", result.get(0).getFirstname());
    }
}