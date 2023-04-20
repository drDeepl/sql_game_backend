package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.Pet;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PersonRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PetRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class PetService {
    private final PetRepository petRepository;
    private final PersonRepository personRepository;

    private final BindingResultChecker bindingResultChecker = new BindingResultChecker();

    @Autowired
    public PetService(PetRepository petRepository, PersonRepository personRepository) {
        this.petRepository = petRepository;
        this.personRepository = personRepository;
    }

    public List<Pet> findAll() throws EntityNotFoundException {
        return petRepository.findAll();
    }

    public void create(Pet pet, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(pet.getId()))
            throw new NotFoundException(String.format("Row with id %s already exists", pet.getId()));
        if (!existsByPersonId(pet.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", pet.getPerson_id()));
        petRepository.save(pet);
    }

    public void update(Pet pet, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(pet.getId()))
            throw new NotFoundException(String.format("Row with id %s was not found", pet.getId()));
        if (!existsByPersonId(pet.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", pet.getPerson_id()));
        petRepository.save(pet);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        petRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return petRepository.existsById(id);
    }

    public boolean existsByPersonId(long id) {
        return personRepository.existsById(id);
    }
}
