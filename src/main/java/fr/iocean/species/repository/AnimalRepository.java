package fr.iocean.species.repository;
import fr.iocean.species.enums.Sex;
import fr.iocean.species.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Animal save(Animal animal);

    List<Animal> findAll();

    Optional<Animal> findById(Integer id);

    void deleteById(Integer id);

    List<Animal> findBySpecies(Species species);

    List<Animal> findByColorIn(List<String> colors);

    @Query("SELECT COUNT(a) FROM Animal a WHERE a.sex = :sex")
    Long countAnimalsBySex(@Param("sex") Sex sex);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Person p JOIN p.animals a WHERE a = :animal")
    boolean doesAnimalBelongToAnyPerson(@Param("animal") Animal animal);

}