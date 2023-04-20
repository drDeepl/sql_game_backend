package ru.sqlinvestigation.RestAPI.repositories.gameDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sqlinvestigation.RestAPI.models.gameDB.City;
import ru.sqlinvestigation.RestAPI.models.gameDB.EducationalInstitution;

@Repository
public interface EDURepository extends JpaRepository<EducationalInstitution, Long> {
}
