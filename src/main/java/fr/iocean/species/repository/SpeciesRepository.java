package fr.iocean.species.repository;
import fr.iocean.species.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {
    Species save(Species species);

    List<Species> findAll();

    Optional<Species> findById(Integer id);

    void deleteById(Integer id);

    Species findFirstByCommonName(String commonName);

    List<Species> findByLatinNameIgnoreCaseContaining(String latinName);

    @Query("SELECT s FROM Species s ORDER BY s.commonName ASC")
    List<Species> findAllByOrderByCommonNameAsc();

    @Query("SELECT s FROM Species s WHERE LOWER(s.commonName) LIKE LOWER(CONCAT('%', :commonName, '%'))")
    List<Species> findByCommonNameContainingIgnoreCase(String commonName);
}