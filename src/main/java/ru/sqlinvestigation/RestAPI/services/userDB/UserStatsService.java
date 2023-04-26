package ru.sqlinvestigation.RestAPI.services.userDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.userDB.UserStats;
import ru.sqlinvestigation.RestAPI.repositories.userDB.StoryRepository;
import ru.sqlinvestigation.RestAPI.repositories.userDB.UserRepository;
import ru.sqlinvestigation.RestAPI.repositories.userDB.UserStatsRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserStatsService {
    private final UserStatsRepository userStatsRepo;
    private final UserRepository userRepo;
    private final StoryRepository storyRepo;
    private final BindingResultChecker bindingResultChecker;

    @Autowired
    public UserStatsService(UserStatsRepository userStatsRepo, UserRepository userRepo, StoryRepository storyRepo, BindingResultChecker bindingResultChecker) {
        this.userStatsRepo = userStatsRepo;
        this.userRepo = userRepo;
        this.storyRepo = storyRepo;
        this.bindingResultChecker = bindingResultChecker;
    }

    public List<UserStats> findAll() throws EntityNotFoundException {
        return userStatsRepo.findAll();
    }

    public List<UserStats> findByUserId(long id) throws EntityNotFoundException{
        return userStatsRepo.findAllByUserId(id);
    }

    public List<UserStats> findAllByStoryId(long id) throws EntityNotFoundException{
        return userStatsRepo.findAllByStoryId(id);
    }

    public void create(UserStats story, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(story.getId()))
            throw new NotFoundException(String.format("Row with id %s already exists", story.getId()));
        if (!existsByUserId(story.getUser_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", story.getUser_id()));
        if (!existsByStoryId(story.getStory_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", story.getStory_id()));
        userStatsRepo.save(story);
    }

    @Transactional(transactionManager = "userTransactionManager")
    public void update(UserStats userStats, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(userStats.getId()))
            throw new NotFoundException(String.format("Row with id %s was not found", userStats.getId()));
        if (!existsByUserId(userStats.getUser_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", userStats.getUser_id()));
        if (!existsByStoryId(userStats.getStory_id()))
            throw new NotFoundException(String.format("Row with id %s was not found", userStats.getStory_id()));
        userStatsRepo.save(userStats);
    }

    @Transactional(transactionManager = "userTransactionManager")
    public void delete(long id) {
        if (!existsById(id)) throw new NotFoundException(String.format("Entity with id %s not found", id));
        userStatsRepo.deleteById(id);
    }

    public boolean existsById(long id) {
        return userStatsRepo.existsById(id);
    }

    public boolean existsByUserId(long id) {
        return userRepo.existsById(id);
    }

    public boolean existsByStoryId(long id) {
        return storyRepo.existsById(id);
    }

}
