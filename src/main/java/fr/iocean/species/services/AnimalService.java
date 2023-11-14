package fr.iocean.species.services;

import fr.iocean.species.advices.ControllerExceptionHandler;
import fr.iocean.species.dto.AnimalDto;
import fr.iocean.species.mappers.AnimalMapper;
import fr.iocean.species.model.Animal;
import fr.iocean.species.repository.AnimalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    /**public List<Animal> findAll() {
        return this.animalRepository.findAll();
    }**/

    public List<AnimalDto> findAll() {
        List<Animal> persons = animalRepository.findAll();
        return persons.stream()
                .map(AnimalMapper::toDto)
                .collect(Collectors.toList());
    }

    public Animal findById(Integer id) {
        return this.animalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Animal create(@RequestBody @Valid Animal animalToCreate) {
        if (animalToCreate.getId() == null || animalToCreate.getId() == 0) {
            throw new ControllerExceptionHandler.EntityToCreateHasAnIdException("Animal ne contient pas d'id, impossible de créer");
        }
        return this.animalRepository.save(animalToCreate);
    }

    public Animal update(@RequestBody @Valid Animal updatedAnimal) {
        if (updatedAnimal.getId() == null || updatedAnimal.getId() == 0) {
            throw new ControllerExceptionHandler.EntityToUpdateHasAnIdException("Animal ne contient pas d'id, impossible de modifier");
        }
        return this.animalRepository.save(updatedAnimal);
    }

    public void delete(@Valid Animal deletedAnimal) {
        this.animalRepository.delete(deletedAnimal);
    }

    public Page<Animal> findAllPages(Pageable pageable) {
        return this.animalRepository.findAll(pageable);
    }
}
