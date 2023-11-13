package fr.iocean.species.controller;

import fr.iocean.species.model.Animal;
import fr.iocean.species.services.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> findAll(){
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    public Animal getPersonById(@PathVariable("id") Integer id) {
        return animalService.findById(id);
    }

    @PostMapping
    public Animal createPerson(@RequestBody @Valid Animal animalToCreate) {
        return this.animalService.create(animalToCreate);
    }
    @PutMapping
    public Animal updatePerson(@RequestBody @Valid Animal updatedAnimal) {
        return this.animalService.update(updatedAnimal);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Integer id) {
        final Animal animal = animalService.findById(id);
        animalService.delete(animal);
    }

    @GetMapping("/pages")
    public Page<Animal> findAllPages(Pageable pageable) {
        return animalService.findAllPages(pageable);
    }

}