package fr.iocean.species.mappers;

import fr.iocean.species.dto.AnimalDto;
import fr.iocean.species.model.Animal;

import java.util.stream.Collectors;

public class AnimalMapper {

    public static AnimalDto toDto(Animal animal) {
        AnimalDto dto = new AnimalDto();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setSpecies(animal.getSpecies().getCommonName());
        dto.setColor(animal.getColor());

        if (animal.getOwner() != null && !animal.getOwner().isEmpty()) {
            dto.setPersons(animal.getOwner().stream()
                    .map(person -> person.getLastname() + " " + person.getFirstname())
                    .collect(Collectors.joining(", ")));
        }

        return dto;
    }
}

