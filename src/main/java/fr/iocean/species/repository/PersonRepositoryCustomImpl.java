package fr.iocean.species.repository;
import fr.iocean.species.model.Person;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Random;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public PersonRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    /**
     * A cause de l'erreur SQL Error: 1093, SQLState: HY000
     * ERROR 5652 --- [  restartedMain] o.h.engine.jdbc.spi.SqlExceptionHelper   : You can't specify target table 'person_animals' for update in FROM clause
     *
     */
    public void deletePeopleWithoutAnimals() {
        Query query = entityManager.createQuery("DELETE FROM Person p WHERE p.animals IS EMPTY");
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void generateRandomEntities(int numberOfEntities) {
        Random random = new Random();
        for (int i = 0; i < numberOfEntities; i++) {
            Person person = new Person();
            person.setAge(random.nextInt(75));
            person.setFirstname(generateRandomFirstName());
            person.setLastname(generateRandomLastName());
            entityManager.persist(person);
        }
    }

    private String generateRandomFirstName() {
        String[] names = {"Clément", "Kelyan", "Guillaume", "Mattéo", "Alexis", "Quentin", "Ryan", "Ruben", "Anna", "Coline"};
        Random random = new Random();
        int randomIndex = random.nextInt(names.length);
        return names[randomIndex];
    }

    private String generateRandomLastName() {
        String[] names = {"Olivier", "Werthe", "Fort", "Andriamisy", "Gardavot", "Parigot", "Mattey", "Hardy", "Pigeot", "Nguyen"};
        Random random = new Random();
        int randomIndex = random.nextInt(names.length);
        return names[randomIndex];
    }
}
