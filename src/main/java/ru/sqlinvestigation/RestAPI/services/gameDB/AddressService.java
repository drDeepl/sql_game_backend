package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.Address;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.AddressRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.CityRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final BindingResultChecker bindingResultChecker = new BindingResultChecker();

    @Autowired
    public AddressService(AddressRepository addressRepository, CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }

    public List<Address> findAll() throws EntityNotFoundException {
        return addressRepository.findAll();
    }

    public void create(Address address, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(address.getAddress_id()))
            throw new NotFoundException(String.format("Row with id %s already exists", address.getAddress_id()));
        if (!existsByCityId(address.getCity_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", address.getCity_id()));
        addressRepository.save(address);
    }

    public void update(Address address, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(address.getAddress_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", address.getAddress_id()));
        if (!existsByCityId(address.getCity_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", address.getCity_id()));
        addressRepository.save(address);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        addressRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return addressRepository.existsById(id);
    }

    public boolean existsByCityId(long id) {
        return cityRepository.existsById(id);
    }

}
