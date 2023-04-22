package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.EducationalInstitution;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.AddressRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.EDURepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class EDUService {
    private final EDURepository eduRepository;
    private final AddressRepository addressRepository;

    private final BindingResultChecker bindingResultChecker = new BindingResultChecker();

    @Autowired
    public EDUService(EDURepository eduRepository, AddressRepository addressRepository) {
        this.eduRepository = eduRepository;
        this.addressRepository = addressRepository;
    }

    public List<EducationalInstitution> findAll() throws EntityNotFoundException {
        return eduRepository.findAll();
    }

    public void create(EducationalInstitution edu, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(edu.getEdu_id()))
            throw new NotFoundException(String.format("Row with id %s already exists", edu.getEdu_id()));
        if (!existsByAddressId(edu.getAddress_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", edu.getAddress_id()));
        eduRepository.save(edu);
    }

    public void update(EducationalInstitution edu, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(edu.getEdu_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", edu.getEdu_id()));
        if (!existsByAddressId(edu.getAddress_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", edu.getAddress_id()));
        eduRepository.save(edu);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        eduRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return eduRepository.existsById(id);
    }

    public boolean existsByAddressId(long id) {
        return addressRepository.existsById(id);
    }
}
