package ru.sqlinvestigation.RestAPI.services.userDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.userDB.Story;
import ru.sqlinvestigation.RestAPI.repositories.userDB.StoryRepository;
import ru.sqlinvestigation.RestAPI.util.BindingResultChecker;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryService {
    private final StoryRepository storyRepository;
    private final BindingResultChecker bindingResultChecker;

    @Autowired
    public StoryService(StoryRepository storyRepository, BindingResultChecker bindingResultChecker) {
        this.storyRepository = storyRepository;
        this.bindingResultChecker = bindingResultChecker;
    }

    public List<Story> findAll() throws EntityNotFoundException {
        return storyRepository.findAll();
    }

    public void create(Story story, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        if (existsById(story.getId()))
            throw new NotFoundException(String.format("Row with id %s already exists", story.getId()));
        storyRepository.save(story);
    }

    @Transactional(transactionManager = "userTransactionManager")
    public void update(Story story, BindingResult bindingResult) {
        bindingResultChecker.check(bindingResult);
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(story.getId()))
            throw new NotFoundException(String.format("Row with id %s was not found", story.getId()));
        storyRepository.save(story);
    }

    @Transactional(transactionManager = "userTransactionManager")
    public void delete(long id) {
        if (!existsById(id))
            throw new NotFoundException(String.format("Entity with id %s not found", id));
        storyRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return storyRepository.existsById(id);
    }

    public List<Story> findAllNames() {
        var list = storyRepository.findAll();
        return list.stream()
                .map(story -> new Story(story.getId(), story.getTitle(), story.getDifficulty(), story.getDescription()))
                .collect(Collectors.toList());
    }


}
