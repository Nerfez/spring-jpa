package fr.iocean.species.services;

import fr.iocean.species.model.Species;
import fr.iocean.species.repository.SpeciesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;

    public List<Species> findAll() {
        return this.speciesRepository.findAll();
    }
    public Species findById(Integer id) {
        return this.speciesRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Species create(@Valid Species speciesToCreate) {
        return this.speciesRepository.save(speciesToCreate);
    }

    public Species update(@Valid Species updatedSpecies) {
        return this.speciesRepository.save(updatedSpecies);
    }

    public void delete(@Valid Species deletedSpecies) {
        this.speciesRepository.delete(deletedSpecies);
    }

    public Page<Species> findAllPages(Pageable pageable) {
        return this.speciesRepository.findAll(pageable);
    }
}
