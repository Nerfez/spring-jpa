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


    /**
     * Pour ajouter un animal
     * @param animal
     * @return Animal
     */
    Animal save(Animal animal);


    /**
     * Pour récupérer ma liste de tous mes animaux
     * @return List<Animal>
     */
    List<Animal> findAll();


    /**
     * Pour trouver un animal par son id
     * @param id
     * @return Optional<Animal>
     */
    Optional<Animal> findById(Integer id);


    /**
     * Pour supprimer un animal par son id
     * @param id
     */
    void deleteById(Integer id);


    /**
     * Pour récupérer ma liste d'animaux qui sont tous issus de la même espece
     * @param species
     * @return List<Animal>
     */
    List<Animal> findBySpecies(Species species);


    /**
     * Pour récupérer ma liste d'animaux qui comportent une couleur de notre liste de couleurs
     * @param colors
     * @return List<Animal>
     */
    List<Animal> findByColorIn(List<String> colors);


    /**
     * Pour récupérer le nombre d'animaux étant du sex F ou M
     * @param sex
     * @return Long
     */
    @Query("SELECT COUNT(a) FROM Animal a WHERE a.sex = :sex")
    Long countAnimalsBySex(@Param("sex") Sex sex);


    /**
     * Pour déterminer si un animal possède ua moins un Propriétaire
     * @param animal
     * @return boolean
     */
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Person p JOIN p.animals a WHERE a = :animal")
    boolean doesAnimalBelongToAnyPerson(@Param("animal") Animal animal);

}