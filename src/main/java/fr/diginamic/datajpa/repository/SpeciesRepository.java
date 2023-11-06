package fr.diginamic.datajpa.repository;
import fr.diginamic.datajpa.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {
    Species save(Species species);

    List<Species> findAll();

    Optional<Species> findById(Long id);

    void deleteById(Long id);

    Species findFirstByCommonName(String commonName);

    List<Species> findByLatinNameIgnoreCaseContaining(String latinName);

}