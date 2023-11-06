package fr.diginamic.datajpa;

import fr.diginamic.datajpa.enums.Sex;
import fr.diginamic.datajpa.model.Animal;
import fr.diginamic.datajpa.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpeciesApplication implements CommandLineRunner {

    @Autowired
    private AnimalRepository animalRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpeciesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Afficher la liste des entités avec findAll
        List<Animal> allAnimals = this.animalRepository.findAll();
        System.out.println("Liste de tous les animaux : ");
        for (Animal animal : allAnimals) {
            System.out.println(animal);
        }

        // 2. À l’aide de notre repository, créer quelques entités avec la méthode save
        Animal newAnimal1 = new Animal("Gris tacheté", "Max", Sex.MALE);
        this.animalRepository.save(newAnimal1);

        Animal newAnimal2 = new Animal("Blanc", "Médor", Sex.MALE);
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
    }
}