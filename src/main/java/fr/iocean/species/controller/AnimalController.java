package fr.iocean.species.controller;

import fr.iocean.species.model.Animal;
import fr.iocean.species.services.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public Animal getAnimalById(@PathVariable("id") Integer id) {
        return animalService.findById(id);
    }

    @PostMapping
    public String createAnimal(@RequestBody @Valid Animal animalToCreate) {
        if(animalToCreate.getId() != null) {
            this.animalService.create(animalToCreate);
            return ("Animal créé " + HttpStatus.OK);
        }
        else {
            return ("Id non renseigné " + HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping
    public String updateAnimal(@RequestBody @Valid Animal updatedAnimal) {
        if(updatedAnimal.getId() != null) {
            this.animalService.update(updatedAnimal);
            return ("Animal modifié " + HttpStatus.OK);
        }
        else {
            return ("Id non renseigné " + HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Integer id) {
        final Animal animal = animalService.findById(id);
        animalService.delete(animal);
    }

    @GetMapping("/pages")
    public Page<Animal> findAllPages(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "20") Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return animalService.findAllPages(pageable);
    }

}