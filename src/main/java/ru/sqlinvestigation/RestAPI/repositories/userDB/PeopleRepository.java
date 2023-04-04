package ru.sqlinvestigation.RestAPI.repositories.userDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sqlinvestigation.RestAPI.models.userDB.Person;

import java.util.Optional;


@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
}
