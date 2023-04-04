package ru.sqlinvestigation.RestAPI.controllers.gameDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.sqlinvestigation.RestAPI.models.gameDB.Person;
import ru.sqlinvestigation.RestAPI.services.gameDB.PersonService;
import ru.sqlinvestigation.RestAPI.util.PersonNotCreatedException;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Transactional(transactionManager = "gameTransactionManager")
@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("api/gameDB/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/index")
    public List<Person> getPeople() {
        return personService.findAll(); // Jackson конвертирует эти объекты в JSON
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person,
                                             BindingResult bindingResult ){
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = Collections.singletonList(bindingResult.getFieldError());
            for (FieldError error: errors){
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        personService.save(person);

        //Отправляем HTTP ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
