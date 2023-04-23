package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.City;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.CityRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class CityService {
    private final CityRepository cityRepository;

    private final BindingResultChecker bindingResultChecker;

    @Autowired
    public CityService(CityRepository cityRepository, BindingResultChecker bindingResultChecker) {
        this.cityRepository = cityRepository;
        this.bindingResultChecker = bindingResultChecker;
    }

    public List<City> findAll() throws EntityNotFoundException {
        return cityRepository.findAll();
    }

    public void create(City city, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(city.getCity_id()))
            throw new NotFoundException(String.format("Row with id %s already exists", city.getCity_id()));
        cityRepository.save(city);
    }

    public void update(City city, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(city.getCity_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", city.getCity_id()));
        cityRepository.save(city);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        cityRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return cityRepository.existsById(id);
    }

}
