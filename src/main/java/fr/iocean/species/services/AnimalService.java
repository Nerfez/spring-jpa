package fr.iocean.species.services;

import fr.iocean.species.model.Animal;
import fr.iocean.species.repository.AnimalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> findAll() {
        return this.animalRepository.findAll();
    }
    public Animal findById(Integer id) {
        return this.animalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Animal create(@Valid Animal animalToCreate) {
        return this.animalRepository.save(animalToCreate);
    }

    public Animal update(@Valid Animal updatedAnimal) {
        return this.animalRepository.save(updatedAnimal);
    }

    public void delete(@Valid Animal deletedAnimal) {
        this.animalRepository.delete(deletedAnimal);
    }

    public Page<Animal> findAllPages(Pageable pageable) {
        return this.animalRepository.findAll(pageable);
    }
}
