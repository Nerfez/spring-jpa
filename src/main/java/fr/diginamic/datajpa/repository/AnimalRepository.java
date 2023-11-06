package fr.diginamic.datajpa.repository;
import fr.diginamic.datajpa.model.Animal;
import fr.diginamic.datajpa.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Animal save(Animal animal);

    List<Animal> findAll();

    Optional<Animal> findById(Long id);

    void deleteById(Long id);

    List<Animal> findBySpecies(Species species);

    List<Animal> findByColorIn(List<String> colors);

}