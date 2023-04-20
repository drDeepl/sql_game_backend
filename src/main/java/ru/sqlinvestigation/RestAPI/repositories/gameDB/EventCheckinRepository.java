package ru.sqlinvestigation.RestAPI.repositories.gameDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sqlinvestigation.RestAPI.models.gameDB.Address;
import ru.sqlinvestigation.RestAPI.models.gameDB.EventCheckin;

@Repository
public interface EventCheckinRepository extends JpaRepository<EventCheckin, Long> {
}
