package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.Person;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PersonRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final BindingResultChecker bindingResultChecker;

    @Autowired
    public PersonService(PersonRepository personRepository, BindingResultChecker bindingResultChecker) {
        this.personRepository = personRepository;
        this.bindingResultChecker = bindingResultChecker;
    }

    public List<Person> findAll() throws EntityNotFoundException {
        return personRepository.findAll();
    }

    public void create(Person person, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(person.getPerson_id()))
            throw new NotFoundException(String.format("Row with id %s already exists", person.getPerson_id()));
        personRepository.save(person);
    }

    public void update(Person person, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(person.getPerson_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", person.getPerson_id()));
        personRepository.save(person);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        personRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return personRepository.existsById(id);
    }
}
