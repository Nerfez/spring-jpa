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
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication
@RestController
public class BestioleMVC {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

    public static void main(String[] args) {
        SpringApplication.run(BestioleMVC.class, args);
    }


}
