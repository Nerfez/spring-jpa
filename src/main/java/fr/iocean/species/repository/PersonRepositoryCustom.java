package fr.iocean.species.repository;

import fr.iocean.species.model.Person;

import java.util.List;

public interface PersonRepositoryCustom {
    /**
     * Supprimer toutes les personnes qui n'ont pas d'animaux
     */
    void deletePeopleWithoutAnimals();

    /**
     * Permet de générer des X nouvelles personnes, X étant le param
     * @param numberOfEntities
     */
    void generateRandomEntities(int numberOfEntities);

}
