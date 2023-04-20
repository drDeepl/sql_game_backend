package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.Organization;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.AddressRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.OrganizationRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PersonRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.WorkersRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final AddressRepository addressRepository;
    private final BindingResultChecker bindingResultChecker = new BindingResultChecker();

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, PersonRepository personRepository, WorkersRepository workersRepository, AddressRepository addressRepository) {
        this.organizationRepository = organizationRepository;
        this.addressRepository = addressRepository;
    }

    public List<Organization> findAll() throws EntityNotFoundException {
        return organizationRepository.findAll();
    }

    public void create(Organization organization, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(organization.getOrganization_id()))
            throw new NotFoundException(String.format("Row with id %s already exists", organization.getOrganization_id()));
        if (!existsByAddressId(organization.getAddress_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", organization.getAddress_id()));
        organizationRepository.save(organization);
    }

    public void update(Organization organization, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(organization.getOrganization_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", organization.getOrganization_id()));
        if (!existsByAddressId(organization.getAddress_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", organization.getAddress_id()));
        organizationRepository.save(organization);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        organizationRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return organizationRepository.existsById(id);
    }

    public boolean existsByAddressId(long id) {
        return addressRepository.existsById(id);
    }
}
