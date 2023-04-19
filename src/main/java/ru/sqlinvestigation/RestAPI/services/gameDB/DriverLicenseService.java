package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sqlinvestigation.RestAPI.dto.gameDB.DriverLicenseDTO;
import ru.sqlinvestigation.RestAPI.models.gameDB.DriverLicense;
import ru.sqlinvestigation.RestAPI.models.userDB.Person;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.DriverLicenseRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PersonRepository;
import ru.sqlinvestigation.RestAPI.util.PersonNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class DriverLicenseService {
    private final DriverLicenseRepository driverLicenseRepository;

    @Autowired
    public DriverLicenseService(DriverLicenseRepository driverLicenseRepository) {
        this.driverLicenseRepository = driverLicenseRepository;
    }

    public List<DriverLicense> findAll() throws EntityNotFoundException {
        return driverLicenseRepository.findAll();
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        Optional<DriverLicense> driverLicense = driverLicenseRepository.findById(id);
        if (driverLicense.isPresent()) {
            if (driverLicense.get().getLicense_id() == id)
                driverLicenseRepository.deleteById(id);
        }
    }

    public DriverLicense findOne(long id) {
        Optional<DriverLicense> foundDriverLicense = driverLicenseRepository.findById(id);
        return foundDriverLicense.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void update(DriverLicense driverLicense) {
        Optional<DriverLicense> oldDriverLicense = driverLicenseRepository.findById(driverLicense.getLicense_id());
        if (oldDriverLicense.isPresent()) {
            driverLicenseRepository.save(driverLicense);
        }
    }
    public boolean existsById(DriverLicense driverLicense){
        return driverLicenseRepository.existsById(driverLicense.getLicense_id());
    }

    public void create(DriverLicense driverLicense) {
        driverLicenseRepository.save(driverLicense);
    }
}
