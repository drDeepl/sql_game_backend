package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.Interview;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.CrimeSceneReportRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.InterviewRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PersonRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class InterviewService {
    private final InterviewRepository interviewRepository;
    private final PersonRepository personRepository;
    private final CrimeSceneReportRepository crimeSceneReportRepository;
    private final BindingResultChecker bindingResultChecker = new BindingResultChecker();

    @Autowired
    public InterviewService(InterviewRepository interviewRepository, PersonRepository personRepository,
                            CrimeSceneReportRepository crimeSceneReportRepository) {
        this.interviewRepository = interviewRepository;
        this.personRepository = personRepository;
        this.crimeSceneReportRepository = crimeSceneReportRepository;
    }

    public List<Interview> findAll() throws EntityNotFoundException {
        return interviewRepository.findAll();
    }

    public void create(Interview interview, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(interview.getInterview_id()))
            throw new NotFoundException(String.format("Row with id %s already exists", interview.getInterview_id()));
        if (!existsByPersonId(interview.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", interview.getPerson_id()));
        if (!existsByCSRId(interview.getCSR_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", interview.getCSR_id()));
        interviewRepository.save(interview);
    }

    public void update(Interview interview, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(interview.getInterview_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", interview.getInterview_id()));
        if (!existsByPersonId(interview.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", interview.getPerson_id()));
        if (!existsByCSRId(interview.getCSR_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", interview.getCSR_id()));
        interviewRepository.save(interview);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        interviewRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return interviewRepository.existsById(id);
    }

    public boolean existsByPersonId(long id) {
        return personRepository.existsById(id);
    }

    public boolean existsByCSRId(long id) {
        return crimeSceneReportRepository.existsById(id);
    }
}
