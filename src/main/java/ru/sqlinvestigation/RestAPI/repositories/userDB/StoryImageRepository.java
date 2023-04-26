package ru.sqlinvestigation.RestAPI.repositories.userDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sqlinvestigation.RestAPI.models.userDB.StoryImage;


@Repository
public interface StoryImageRepository extends JpaRepository<StoryImage, Long> {
}
