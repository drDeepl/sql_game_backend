package ru.sqlinvestigation.RestAPI.services.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sqlinvestigation.RestAPI.dto.gameDB.TestDTO;
import ru.sqlinvestigation.RestAPI.models.gameDB.Person;
import ru.sqlinvestigation.RestAPI.repositories.gameDB.PersonRepository;
import ru.sqlinvestigation.RestAPI.util.PersonNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
//@Transactional(readOnly = true)
//@Transactional(readOnly = true, transactionManager = "gameTransactionManager")
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public List<Person> findAll() throws EntityNotFoundException {
        return personRepository.findAll();
    }

//    public List<Person> findAll2() throws EntityNotFoundException {
//        List<Person> people = personRepository.findAll();
//        List<TestDTO> testDTOS;
//        for (int i = 0; i < people.size(); i++) {
//            testDTOS[0]
//        }
//        testDTOS.set
//        people
//
//        return personRepository.findAll(PageRequest.of(0, 100)).getContent();
//    }


//    public TestDTO mapToProductDto(ProductEntity entity){
//        TestDTO dto = new TestDTO();
//        dto.setId(entity.getId());
//        dto.setName(entity.getName());
//        dto.setPurchasePrice(entity.getPurchasePrice());
//        return dto;
//    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }
    @Transactional(transactionManager = "gameTransactionManager")
    public void save(Person person){
        personRepository.save(person);
    }
}
