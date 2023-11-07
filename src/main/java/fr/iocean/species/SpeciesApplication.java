package fr.iocean.species;

import fr.iocean.species.enums.Sex;
import fr.iocean.species.model.Animal;
import fr.iocean.species.model.Person;
import fr.iocean.species.repository.AnimalRepository;
import fr.iocean.species.repository.PersonRepository;
import fr.iocean.species.repository.SpeciesRepository;
import fr.iocean.species.model.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;

@SpringBootApplication
@ComponentScan(basePackages = "fr.iocean.species")
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

        //**********************************   TP - 03  **********************************

        // 1. Afficher la liste des entités avec findAll
        List<Animal> allAnimals = this.animalRepository.findAll();
        System.out.println("Liste de tous les animaux : ");
        for (Animal animal : allAnimals) {
            System.out.println(animal);
        }

        // 2. À l’aide de notre repository, créer quelques entités avec la méthode save
        //Ca fonctionne bien mais je pense en commentaire pour eviter de push une valeur a chaque lancement
        //Animal newAnimal1=  new Animal("Gris tacheté", "Félix", Sex.M, speciesRepository.findById(1).get());
        //this.animalRepository.save(newAnimal1);

        Animal newAnimal2 = new Animal("Blanc", "Médor", Sex.F, speciesRepository.findById(2).get());
        this.animalRepository.save(newAnimal2);

        // 3. Rechercher une entité par son id avec findById
        Optional<Animal> retrievedAnimal = this.animalRepository.findById(animalRepository.findById(1).orElse(null).getId());
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
        String commonNameToSearch = "Chien";
        Species species = speciesRepository.findFirstByCommonName(commonNameToSearch);

        if (species != null) {
            System.out.println("Espèce trouvée : " + species.getCommonName());
        } else {
            System.out.println("Aucune espèce trouvée avec le nom commun : " + commonNameToSearch);
        }

        //METHODE findByLatinNameIgnoreCaseContaining
        String latinNameToSearch = "Chien";
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
        List<String> colorsToSearch = Arrays.asList("Blanc", "Gris tacheté");

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

        //**********************************  TP - 05 - 1 **********************************
        //Methode findAllByOrderByCommonNameAsc
        List<Species> speciesList = speciesRepository.findAllByOrderByCommonNameAsc();

        if (!speciesList.isEmpty()) {
            System.out.println("Liste d'espèces ordonnées par nom commun ascendant :");
            for (Species speciesFound : speciesList) {
                System.out.println("Nom commun : " + speciesFound.getCommonName() + " - Nom latin : " + speciesFound.getLatinName());
            }
        } else {
            System.out.println("Aucune espèce trouvée.");
        }

        //Methode findByCommonNameContainingIgnoreCase
        commonNameToSearch = "Chat";

        matchingSpecies = speciesRepository.findByCommonNameContainingIgnoreCase(commonNameToSearch);

        if (!matchingSpecies.isEmpty()) {
            System.out.println("Espèces dont le nom commun contient la chaîne spécifiée (en ignorant la casse) :");
            for (Species speciesFound2 : matchingSpecies) {
                System.out.println("Nom commun : " + speciesFound2.getCommonName() + " - Nom latin : " + speciesFound2.getLatinName());
            }
        } else {
            System.out.println("Aucune espèce trouvée avec le nom commun contenant : " + commonNameToSearch);
        }
        //********************************** FIN  TP - 05 - 1 **********************************

        //**********************************  TP - 05 - 2 **********************************
        //Methode findPeopleByAgeRange
        List<Person> peopleInRange = personRepository.findPeopleByAgeRange(20, 40);

        if (!peopleInRange.isEmpty()) {
            System.out.println("Liste de personnes dont l'âge est compris entre 20 et 40 :");
            for (Person personFounded : peopleInRange) {
                System.out.println("Prenom : " + personFounded.getFirstname() + " Nom : " + personFounded.getLastname());
            }
        } else {
            System.out.println("Aucune personne trouvée.");
        }

        //Methode findPeopleWithAnimal
        List<Person> peopleWithAnimal = personRepository.findPeopleWithAnimal(animalRepository.findById(1).orElse(null));

        if (!peopleWithAnimal.isEmpty()) {
            System.out.println("Personnes dont le nom qui possède l'animal 1 :");
            for (Person person : peopleWithAnimal) {
                System.out.println("Prenom : " + person.getFirstname() + " Nom : " + person.getLastname());
            }
        } else {
            System.out.println("Aucune personne trouvée contenant l'animal : " + animalRepository.findById(1).orElse(null).getName());
        }
        //********************************** FIN  TP - 05 - 2 **********************************

        //**********************************  TP - 05 - 3 **********************************
        //Methode countAnimalsBySex
        Long animalsNumber = animalRepository.countAnimalsBySex(Sex.M);

        System.out.println("Nombre d'animaux étant du sex Masculin :" + animalsNumber);

        //Méthode doesAnimalBelongToAnyPerson
        Animal animalToSearch = animalRepository.findById(2).orElse(null);
        boolean isValid = animalRepository.doesAnimalBelongToAnyPerson(animalToSearch);
        if(isValid) {
            System.out.println("L'animal " + animalToSearch.getName() + " est lié à au moins 1 personne");
        } else {
            System.out.println("L'animal " + animalToSearch.getName() + " n'est lié à personne");
        }
        //********************************** FIN  TP - 05 - 3 **********************************

        //**********************************  TP - 06 - 1 **********************************
        //Méthode deletePeopleWithoutAnimals
        personRepository.deletePeopleWithoutAnimals();
        //********************************** FIN  TP - 06 - 1 **********************************

        //**********************************  TP - 06 - 2 **********************************
        //Méthode generateRandomEntities
        //Fonctionne correctement, on peut décommenter pour tester mais je l'ai mis en commentaire aussi pour éviter de push vers ma bdd a chaque lancement
        //personRepository.generateRandomEntities(10);

        //********************************** FIN  TP - 06 - 2 **********************************
    }
}