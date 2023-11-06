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

import java.util.Arrays;
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

        //**********************************   TP - 03  **********************************

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
        //**********************************   FIN TP - 03  **********************************


        //**********************************   TP - 04 - 1 - Species **********************************
        //METHODE findFirstByCommonName
        String commonNameToSearch = "NomCommunRecherche";
        Species species = speciesRepository.findFirstByCommonName(commonNameToSearch);

        if (species != null) {
            System.out.println("Espèce trouvée : " + species.getCommonName());
        } else {
            System.out.println("Aucune espèce trouvée avec le nom commun : " + commonNameToSearch);
        }

        //METHODE findByLatinNameIgnoreCaseContaining
        String latinNameToSearch = "NomLatinRecherche";
        List<Species> matchingSpecies = speciesRepository.findByLatinNameIgnoreCaseContaining(latinNameToSearch);

        if (!matchingSpecies.isEmpty()) {
            System.out.println("Espèces correspondantes trouvées :");
            for (Species species1 : matchingSpecies) {
                System.out.println(species1.getLatinName());
            }
        } else {
            System.out.println("Aucune espèce trouvée avec le nom latin correspondant : " + latinNameToSearch);
        }
        //********************************** FIN  TP - 04 - 1 **********************************



        //********************************** TP - 04 - 2 - Person **********************************
        //METHODE findByLastNameOrFirstName

        String lastNameToSearch = "NomRecherche";
        String firstNameToSearch = "PrenomRecherche";

        List<Person> matchingPeople = personRepository.findByLastNameOrFirstName(lastNameToSearch, firstNameToSearch);

        if (!matchingPeople.isEmpty()) {
            System.out.println("Personnes correspondantes trouvées :");
            for (Person person : matchingPeople) {
                System.out.println(person.getLastname() + " " + person.getFirstname());
            }
        } else {
            System.out.println("Aucune personne trouvée avec le nom de famille ou le prénom correspondant : " + lastNameToSearch + " " + firstNameToSearch);
        }

        //METHODE findByAgeGreaterThanEqual
        int ageToSearch = 30;

        List<Person> olderPeople = personRepository.findByAgeGreaterThanEqual(ageToSearch);

        if (!olderPeople.isEmpty()) {
            System.out.println("Personnes plus âgées ou égales trouvées :");
            for (Person person : olderPeople) {
                System.out.println(person.getLastname() + " " + person.getFirstname() + " - Âge : " + person.getAge());
            }
        } else {
            System.out.println("Aucune personne trouvée avec l'âge supérieur ou égal à : " + ageToSearch);
        }
        //********************************** FIN  TP - 04 - 2 **********************************


        //********************************** TP - 04 - 3 - Animal **********************************
        //METHODE findBySpecies
        Species speciesToSearch = speciesRepository.findById(1L).orElse(null);

        if (speciesToSearch != null) {
            List<Animal> animalsOfSpecies = animalRepository.findBySpecies(speciesToSearch);

            if (!animalsOfSpecies.isEmpty()) {
                System.out.println("Animaux de l'espèce trouvés :");
                for (Animal animal : animalsOfSpecies) {
                    System.out.println(animal.getName() + " - Espèce : " + animal.getSpecies().getCommonName());
                }
            } else {
                System.out.println("Aucun animal trouvé pour l'espèce : " + speciesToSearch.getCommonName());
            }
        } else {
            System.out.println("L'espèce spécifiée n'a pas été trouvée.");
        }

        //METHODE findByColorIn
        List<String> colorsToSearch = Arrays.asList("Blanc", "Noir");

        List<Animal> animalsWithColors = animalRepository.findByColorIn(colorsToSearch);

        if (!animalsWithColors.isEmpty()) {
            System.out.println("Animaux avec les couleurs spécifiées trouvés :");
            for (Animal animal : animalsWithColors) {
                System.out.println(animal.getName() + " - Couleur : " + animal.getColor());
            }
        } else {
            System.out.println("Aucun animal trouvé avec les couleurs spécifiées : " + colorsToSearch);
        }

        //********************************** FIN  TP - 04 - 3 **********************************

    }
}