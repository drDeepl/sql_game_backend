package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sqlinvestigation.RestAPI.models.gameDB.Person;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PersonRepository;
import ru.sqlinvestigation.RestAPI.util.PersonNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() throws EntityNotFoundException {
        return personRepository.findAll();
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(int id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            if (person.get().getPerson_id() == id)
                personRepository.deleteById(id);
        }
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void update(Person person) {
        Optional<Person> oldPerson = personRepository.findById(person.getPerson_id());
        if (oldPerson.isPresent()) {
            personRepository.save(person);
        }
    }
    public boolean existsById(Person person){
        return personRepository.existsById(person.getPerson_id());
    }

    public void create(Person person) {
        personRepository.save(person);
    }
}
