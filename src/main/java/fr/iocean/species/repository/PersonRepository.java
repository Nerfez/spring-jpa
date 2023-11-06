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

    Person save(Person person);

    List<Person> findAll();

    Optional<Person> findById(Integer id);

    void deleteById(Integer id);

    //J'ai eu des soucis avec la nomenclature lastname qui était reconnu comme lastName et causait des problèmes donc j'ai utilisé Query
    @Query("SELECT p FROM Person p WHERE p.lastname = :lastname OR p.firstname = :firstname")
    List<Person> findByLastNameOrFirstName(@Param("lastname") String lastname, @Param("firstname") String firstname);

    List<Person> findByAgeGreaterThanEqual(int age);

    @Query("SELECT p FROM Person p WHERE p.age BETWEEN :minAge AND :maxAge")
    List<Person> findPeopleByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

    @Query("SELECT p FROM Person p JOIN p.animals a WHERE a = :animal")
    List<Person> findPeopleWithAnimal(@Param("animal") Animal animal);
}
