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
    public void deletePeopleWithoutAnimals() {
        //On récupère les ids de personne qui n'ont pas d'animaux
        Query selectQuery = entityManager.createNativeQuery("SELECT p.id FROM person p " +
                "WHERE p.id NOT IN (SELECT pa.person_id FROM person_animals pa)");

        //Stockés dans cette liste
        List<Integer> idsToDelete = selectQuery.getResultList();

        if (!idsToDelete.isEmpty()) {
            //On les supprimes
            Query deleteQuery = entityManager.createNativeQuery("DELETE FROM person WHERE id IN :ids");
            deleteQuery.setParameter("ids", idsToDelete);
            deleteQuery.executeUpdate();
        }
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
