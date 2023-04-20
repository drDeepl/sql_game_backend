package ru.sqlinvestigation.RestAPI.repositories.gameDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sqlinvestigation.RestAPI.models.gameDB.Pet;
import ru.sqlinvestigation.RestAPI.models.gameDB.Workers;

@Repository
public interface WorkersRepository extends JpaRepository<Workers, Long> {
}
