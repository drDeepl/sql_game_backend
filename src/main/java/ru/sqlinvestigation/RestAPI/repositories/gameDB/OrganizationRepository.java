package ru.sqlinvestigation.RestAPI.repositories.gameDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sqlinvestigation.RestAPI.models.gameDB.Organization;
import ru.sqlinvestigation.RestAPI.models.gameDB.Pet;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
