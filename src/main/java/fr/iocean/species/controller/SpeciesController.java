package fr.iocean.species.controller;

import fr.iocean.species.model.Species;
import fr.iocean.species.services.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {

    @Autowired
    SpeciesService speciesService;

    @GetMapping
    public List<Species> findAll(){
        return speciesService.findAll();
    }

    @GetMapping("/{id}")
    public Species getPersonById(@PathVariable("id") Integer id) {
        return speciesService.findById(id);
    }

    @PostMapping
    public void createPerson(@RequestBody @Valid Species speciesToCreate) {
        if(speciesToCreate.getId() != null) {
            System.out.println("Id non renseigné " + HttpStatus.BAD_REQUEST);
        }
        else {
            this.speciesService.create(speciesToCreate);
        }
    }

    @PutMapping
    public void updatePerson(@RequestBody @Valid Species updatedSpecies) {
        if(updatedSpecies.getId() != null) {
            System.out.println("Id non renseigné " + HttpStatus.BAD_REQUEST);
        }
        else {
            this.speciesService.update(updatedSpecies);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Integer id) {
        final Species species = speciesService.findById(id);
        speciesService.delete(species);
    }

    @GetMapping("/pages")
    public Page<Species> findAllPages(Pageable pageable) {
        return speciesService.findAllPages(pageable);
    }
}
