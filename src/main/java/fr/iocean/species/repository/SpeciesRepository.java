package fr.iocean.species.repository;
import fr.iocean.species.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {

    /**
     * Pour ajouter une espece
     * @param species
     * @return Species
     */
    Species save(Species species);


    /**
     * Pour récupérer ma liste de toutes mes especes
     * @return List<Species>
     */
    List<Species> findAll();


    /**
     * Pour récupérer une espece par sonid
     * @param id
     * @return Optional<Species>
     */
    Optional<Species> findById(Integer id);


    /**
     * Pour supprimer une espece par son id
     * @param id
     */
    void deleteById(Integer id);


    /**
     * Pour récupérer une espece par son commonName
     * @param commonName
     * @return Species
     */
    Species findFirstByCommonName(String commonName);


    /**
     * Pour récupérer une liste d espece par son latinName
     * @param latinName
     * @return List<Species>
     */
    List<Species> findByLatinNameIgnoreCaseContaining(String latinName);


    /**
     * Pour récupérer la liste d especes en ordonnant la liste par le commonName
     * @return List<Species>
     */
    @Query("SELECT s FROM Species s ORDER BY s.commonName ASC")
    List<Species> findAllByOrderByCommonNameAsc();


    /**
     * Pour récupérer une liste d especes par le commonName
     * @param commonName
     * @return List<Species>
     */
    @Query("SELECT s FROM Species s WHERE LOWER(s.commonName) LIKE LOWER(CONCAT('%', :commonName, '%'))")
    List<Species> findByCommonNameContainingIgnoreCase(String commonName);
}