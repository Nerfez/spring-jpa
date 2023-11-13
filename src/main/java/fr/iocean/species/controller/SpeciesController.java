package fr.iocean.species.controller;

import fr.iocean.species.model.Person;
import fr.iocean.species.model.Species;
import fr.iocean.species.services.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Species createPerson(@RequestBody @Valid Species speciesToCreate) {
        return this.speciesService.create(speciesToCreate);
    }
    @PutMapping
    public Species updatePerson(@RequestBody @Valid Species updatedSpecies) {
        return this.speciesService.update(updatedSpecies);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final Integer id) {
        final Species species = speciesService.findById(id);
        speciesService.delete(species);
    }
}
