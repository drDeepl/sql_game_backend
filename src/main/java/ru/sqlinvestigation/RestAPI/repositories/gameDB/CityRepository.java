package ru.sqlinvestigation.RestAPI.repositories.gameDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sqlinvestigation.RestAPI.models.gameDB.Address;
import ru.sqlinvestigation.RestAPI.models.gameDB.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
