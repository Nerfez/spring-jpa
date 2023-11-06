package fr.diginamic.datajpa;

import fr.diginamic.datajpa.enums.Sex;
import fr.diginamic.datajpa.model.*;
import fr.diginamic.datajpa.repository.AnimalRepository;
import fr.diginamic.datajpa.repository.PersonRepository;
import fr.diginamic.datajpa.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SpeciesApplication implements CommandLineRunner {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpeciesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // exemples utilisés du dump (1,'Chat','Felis silvestris catus'),(2,'Chien','Canis lupus familiaris')
        Species newSpecies1 = new Species("Chat", "Felis silvestris catus");
        Species newSpecies2 = new Species("Chien", "Canis lupus familiaris");
        Species newSpecies3 = new Species("Chat", "Nom random");
        this.speciesRepository.save(newSpecies1);
        this.speciesRepository.save(newSpecies2);
        this.speciesRepository.save(newSpecies3);

        Person newPerson1 = new Person(22,"Henri","Lamarque");
        Person newPerson2 = new Person(24,"Sylvie","Lamarque");
        Person newPerson3 = new Person(55,"Jean","Vintroi");
        this.personRepository.save(newPerson1);
        this.personRepository.save(newPerson2);
        this.personRepository.save(newPerson3);


        // 1. Afficher la liste des entités avec findAll
        List<Animal> allAnimals = this.animalRepository.findAll();
        System.out.println("Liste de tous les animaux : ");
        for (Animal animal : allAnimals) {
            System.out.println(animal);
        }

        // 2. À l’aide de notre repository, créer quelques entités avec la méthode save
        Animal newAnimal1=  new Animal("Gris tacheté", "Félix", Sex.MALE, newSpecies1);
        this.animalRepository.save(newAnimal1);

        Animal newAnimal2 = new Animal("Blanc", "Médor", Sex.MALE, newSpecies2);
        this.animalRepository.save(newAnimal2);

        // 3. Rechercher une entité par son id avec findById
        Optional<Animal> retrievedAnimal = this.animalRepository.findById(newAnimal1.getId());
        if (retrievedAnimal.isPresent()) {
            System.out.println("Animal trouvé par ID : " + retrievedAnimal.get());
        } else {
            System.out.println("Animal non trouvé par ID");
        }

        // 4. Supprimer une entité avec delete, et afficher la longueur de la liste de toutes les entités pour
        //vérifier qu’elle a bien été supprimée
        this.animalRepository.delete(newAnimal2);

        allAnimals = this.animalRepository.findAll();
        System.out.println("Liste de tous les animaux après la suppression : ");
        for (Animal animal : allAnimals) {
            System.out.println(animal);
        }

        //TP - 04 utiliser le nom des méthodes
        List<Animal> allAnimalsBySpecies = this.animalRepository.findBySpecies(newSpecies1);
        System.out.println("Liste de tous les animaux par especes (Chat) : ");
        for (Animal animal : allAnimalsBySpecies) {
            System.out.println(animal);
        }
    }
}