package fr.diginamic.datajpa.repository;
import fr.diginamic.datajpa.model.Person;
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

    Optional<Person> findById(Long id);

    void deleteById(Long id);

    @Query("SELECT p FROM Person p WHERE p.lastname = :lastname OR p.firstname = :firstname")
    List<Person> findByLastNameOrFirstName(@Param("lastname") String lastname, @Param("firstname") String firstname);

    List<Person> findByAgeGreaterThanEqual(int age);


}
