package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.DriverLicense;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.DriverLicenseRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PersonRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class DriverLicenseService {
    private final DriverLicenseRepository driverLicenseRepository;
    private final PersonRepository personRepository;

    private final BindingResultChecker bindingResultChecker;

    @Autowired
    public DriverLicenseService(DriverLicenseRepository driverLicenseRepository, PersonRepository personRepository, BindingResultChecker bindingResultChecker) {
        this.driverLicenseRepository = driverLicenseRepository;
        this.personRepository = personRepository;
        this.bindingResultChecker = bindingResultChecker;
    }

    public List<DriverLicense> findAll() throws EntityNotFoundException {
        return driverLicenseRepository.findAll();
    }

    public void create(DriverLicense driverLicense, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(driverLicense.getLicense_id()))
            throw new NotFoundException(String.format("Row with id %s already exists", driverLicense.getLicense_id()));
        if (!existsByPersonId(driverLicense.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", driverLicense.getPerson_id()));
        driverLicenseRepository.save(driverLicense);
    }

    public void update(DriverLicense driverLicense, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(driverLicense.getLicense_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", driverLicense.getLicense_id()));
        if (!existsByPersonId(driverLicense.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", driverLicense.getPerson_id()));
        driverLicenseRepository.save(driverLicense);

    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        driverLicenseRepository.deleteById(id);
    }
    public boolean existsById(long id ) {
        return driverLicenseRepository.existsById(id);
    }

    public boolean existsByPersonId(long id) {
        return personRepository.existsById(id);
    }

}
