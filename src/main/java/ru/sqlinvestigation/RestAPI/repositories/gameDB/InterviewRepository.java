package ru.sqlinvestigation.RestAPI.repositories.gameDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sqlinvestigation.RestAPI.models.gameDB.City;
import ru.sqlinvestigation.RestAPI.models.gameDB.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
