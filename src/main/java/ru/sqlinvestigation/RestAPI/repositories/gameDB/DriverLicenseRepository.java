package ru.sqlinvestigation.RestAPI.repositories.gameDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sqlinvestigation.RestAPI.models.gameDB.DriverLicense;
import ru.sqlinvestigation.RestAPI.models.gameDB.Person;

@Repository
public interface DriverLicenseRepository extends JpaRepository<DriverLicense, Long> {
}
