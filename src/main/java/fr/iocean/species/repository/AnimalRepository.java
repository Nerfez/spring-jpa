package fr.iocean.species.repository;
import fr.iocean.species.model.Animal;
import fr.iocean.species.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
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

}