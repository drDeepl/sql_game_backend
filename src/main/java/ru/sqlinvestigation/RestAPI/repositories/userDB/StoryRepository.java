package ru.sqlinvestigation.RestAPI.repositories.userDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sqlinvestigation.RestAPI.models.userDB.Story;
import ru.sqlinvestigation.RestAPI.models.userDB.User;

import java.util.Optional;


@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
}
