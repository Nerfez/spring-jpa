package fr.iocean.species.mappers;

import fr.iocean.species.dto.PersonDto;
import fr.iocean.species.model.Person;

public class PersonMapper {

    public static PersonDto toDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setAge(person.getAge());
        dto.setName(person.getLastname().toUpperCase() + " " + person.getFirstname());

        if (person.getAnimals() != null && !person.getAnimals().isEmpty()) {
            dto.setAnimals(person.getAnimals().stream()
                    .map(animal -> animal.getName() + " (" + animal.getSpecies().getCommonName() + ")")
                    .toArray(String[]::new));
        }
        return dto;
    }
}

