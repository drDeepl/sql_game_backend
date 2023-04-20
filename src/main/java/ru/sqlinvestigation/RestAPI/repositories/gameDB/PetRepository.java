package ru.sqlinvestigation.RestAPI.repositories.gameDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sqlinvestigation.RestAPI.models.gameDB.Person;
import ru.sqlinvestigation.RestAPI.models.gameDB.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
