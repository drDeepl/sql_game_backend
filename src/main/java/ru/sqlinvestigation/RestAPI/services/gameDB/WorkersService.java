package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.Workers;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.OrganizationRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PersonRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.WorkersRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class WorkersService {
    private final WorkersRepository workersRepository;
    private final PersonRepository personRepository;
    private final OrganizationRepository organizationRepository;
    private final BindingResultChecker bindingResultChecker;

    @Autowired
    public WorkersService(WorkersRepository workersRepository, PersonRepository personRepository,
                          OrganizationRepository organizationRepository, BindingResultChecker bindingResultChecker) {
        this.workersRepository = workersRepository;
        this.personRepository = personRepository;
        this.organizationRepository = organizationRepository;
        this.bindingResultChecker = bindingResultChecker;
    }

    public List<Workers> findAll() throws EntityNotFoundException {
        return workersRepository.findAll();
    }

    public void create(Workers workers, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(workers.getWorkers_id()))
            throw new NotFoundException(String.format("Row with id %s already exists", workers.getWorkers_id()));
        if (!existsByPersonId(workers.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", workers.getPerson_id()));
        if (!existsByOrganizationId(workers.getOrganization_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", workers.getOrganization_id()));
        workersRepository.save(workers);
    }

    public void update(Workers workers, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(workers.getWorkers_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", workers.getWorkers_id()));
        if (!existsByPersonId(workers.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", workers.getPerson_id()));
        if (!existsByOrganizationId(workers.getOrganization_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", workers.getOrganization_id()));
        workersRepository.save(workers);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        workersRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return workersRepository.existsById(id);
    }

    public boolean existsByPersonId(long id) {
        return personRepository.existsById(id);
    }

    public boolean existsByOrganizationId(long id) {
        return organizationRepository.existsById(id);
    }
}
