package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.Students;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.EDURepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PersonRepository;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.StudentsRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class StudentsService {
    private final StudentsRepository studentsRepository;
    private final PersonRepository personRepository;
    private final EDURepository eduRepository;
    private final BindingResultChecker bindingResultChecker;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository, PersonRepository personRepository,
                           EDURepository eduRepository, BindingResultChecker bindingResultChecker) {
        this.studentsRepository = studentsRepository;
        this.personRepository = personRepository;
        this.eduRepository = eduRepository;
        this.bindingResultChecker = bindingResultChecker;
    }

    public List<Students> findAll() throws EntityNotFoundException {
        return studentsRepository.findAll();
    }

    public void create(Students students, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(students.getStudents_id()))
            throw new NotFoundException(String.format("Row with id %s already exists", students.getStudents_id()));
        if (!existsByPersonId(students.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", students.getPerson_id()));
        if (!existsByEDUId(students.getEdu_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", students.getEdu_id()));
        studentsRepository.save(students);
    }

    public void update(Students students, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(students.getStudents_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", students.getStudents_id()));
        if (!existsByPersonId(students.getPerson_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", students.getPerson_id()));
        if (!existsByEDUId(students.getEdu_id()))
            throw new NotFoundException(String.format("Entity with id %s not found", students.getEdu_id()));
        studentsRepository.save(students);
    }

    @Transactional(transactionManager = "gameTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        studentsRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return studentsRepository.existsById(id);
    }

    public boolean existsByPersonId(long id) {
        return personRepository.existsById(id);
    }

    public boolean existsByEDUId(long id) {
        return eduRepository.existsById(id);
    }
}
