package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.CrimeSceneReport;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.AddressRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.CityRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.CrimeSceneReportRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class CrimeSceneReportService {
    private final CrimeSceneReportRepository reportRepository;
    private final CityRepository cityRepository;

    private final BindingResultChecker bindingResultChecker = new BindingResultChecker();

    @Autowired
    public CrimeSceneReportService(CrimeSceneReportRepository reportRepository,
                                   CityRepository cityRepository) {
        this.reportRepository = reportRepository;
        this.cityRepository = cityRepository;
    }

    public List<CrimeSceneReport> findAll() throws EntityNotFoundException {
        return reportRepository.findAll();
    }

    public void create(CrimeSceneReport report, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(report.getCSR_id()))
            throw new NotFoundException(String.format("Row with id %s already exists", report.getCSR_id()));
        if (!existsByCityId(report.getCity_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", report.getCity_id()));
        reportRepository.save(report);
    }

    public void update(CrimeSceneReport report, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(report.getCSR_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", report.getCSR_id()));
        if (!existsByCityId(report.getCity_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", report.getCity_id()));
        reportRepository.save(report);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        reportRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return reportRepository.existsById(id);
    }

    public boolean existsByCityId(long id) {
        return cityRepository.existsById(id);
    }
}
