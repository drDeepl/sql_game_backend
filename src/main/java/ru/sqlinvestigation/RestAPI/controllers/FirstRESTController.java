package ru.sqlinvestigation.RestAPI.controllers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sqlinvestigation.RestAPI.models.userDB.Person;
import ru.sqlinvestigation.RestAPI.repositories.userDB.PeopleRepository;
import ru.sqlinvestigation.RestAPI.security.PersonDetails;

import java.util.Optional;

/**
 * @author Neil Alishev
 */
@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("/api")
public class FirstRESTController {

    private final PeopleRepository peopleRepository;

    public FirstRESTController(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello world!";
    }

    @GetMapping("/get/{name}")
    public Person getPerson(@PathVariable("name") String name) {
        return loadUserByUsername(name); // Jackson конвертирует в JSON
    }


    public Person loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return person.get();
    }
}

