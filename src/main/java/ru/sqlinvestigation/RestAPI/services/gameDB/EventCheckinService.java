package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.EventCheckin;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.EventCheckinRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PersonRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class EventCheckinService {
    private final EventCheckinRepository eventCheckinRepository;
    private final PersonRepository personRepository;

    private final BindingResultChecker bindingResultChecker = new BindingResultChecker();

    @Autowired
    public EventCheckinService(EventCheckinRepository eventCheckinRepository, PersonRepository personRepository) {
        this.eventCheckinRepository = eventCheckinRepository;
        this.personRepository = personRepository;
    }

    public List<EventCheckin> findAll() throws EntityNotFoundException {
        return eventCheckinRepository.findAll();
    }

    public void create(EventCheckin eventCheckin, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(eventCheckin.getEC_id()))
            throw new NotFoundException(String.format("Row with id %s already exists", eventCheckin.getEC_id()));
        if (!existsByPersonId(eventCheckin.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", eventCheckin.getPerson_id()));
        eventCheckinRepository.save(eventCheckin);
    }

    public void update(EventCheckin eventCheckin, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(eventCheckin.getEC_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", eventCheckin.getEC_id()));
        if (!existsByPersonId(eventCheckin.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", eventCheckin.getPerson_id()));
        eventCheckinRepository.save(eventCheckin);

    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        eventCheckinRepository.deleteById(id);
    }
    public boolean existsById(long id ) {
        return eventCheckinRepository.existsById(id);
    }

    public boolean existsByPersonId(long id) {
        return personRepository.existsById(id);
    }

}
