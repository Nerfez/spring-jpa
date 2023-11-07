package fr.iocean.species.repository;
import fr.iocean.species.model.Animal;
import fr.iocean.species.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Pour ajouter une personne
     * @param person
     * @return Person
     */
    Person save(Person person);


    /**
     * Pour récupérer ma liste de toutes mes personnes
     * @return List<Person>
     */
    List<Person> findAll();


    /**
     * Pour récupérer une personne par son id
     * @param id
     * @return Optional<Person>
     */
    Optional<Person> findById(Integer id);


    /**
     * Pour supprimer une personne par son id
     * @param id
     */
    void deleteById(Integer id);

    //J'ai eu des soucis avec la nomenclature lastname qui était reconnu comme lastName et causait des problèmes donc j'ai utilisé Query
    /**
     * Pour récupérer une liste de personne qui ont pour nom ou prénom un nom ou prénom recherché
     * @param lastname
     * @param firstname
     * @return List<Person>
     */
    @Query("SELECT p FROM Person p WHERE p.lastname = :lastname OR p.firstname = :firstname")
    List<Person> findByLastNameOrFirstName(@Param("lastname") String lastname, @Param("firstname") String firstname);


    /**
     * Pour récupérer une liste de personne qui ont un age supérieur a celui recherché
     * @param age
     * @return List<Person>
     */
    List<Person> findByAgeGreaterThanEqual(int age);


    /**
     * Pour récupérer une liste de personne qui ont pour age un age compris entre 2 ages donnés
     * @param minAge
     * @param maxAge
     * @return List<Person>
     */
    @Query("SELECT p FROM Person p WHERE p.age BETWEEN :minAge AND :maxAge")
    List<Person> findPeopleByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);


    /**
     * Pour récupérer une liste de personne qui contient un animal
     * @param animal
     * @return List<Person>
     */
    @Query("SELECT p FROM Person p JOIN p.animals a WHERE a = :animal")
    List<Person> findPeopleWithAnimal(@Param("animal") Animal animal);
}
