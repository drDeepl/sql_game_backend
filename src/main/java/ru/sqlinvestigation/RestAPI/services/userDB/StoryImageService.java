package ru.sqlinvestigation.RestAPI.services.userDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.userDB.StoryImage;
import ru.sqlinvestigation.RestAPI.repositories.userDB.StoryImageRepository;
import ru.sqlinvestigation.RestAPI.repositories.userDB.StoryRepository;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoryImageService {
    private final StoryImageRepository storyImageRepository;

    private final StoryRepository storyRepository;

    @Autowired
    public StoryImageService(StoryImageRepository storyImageRepository, StoryRepository storyRepository) {
        this.storyImageRepository = storyImageRepository;
        this.storyRepository = storyRepository;
    }

    public List<Long> findListId() throws EntityNotFoundException {
        return storyImageRepository.findAll().stream().map(StoryImage::getId_stories).collect(Collectors.toList());
    }

    public byte[] findByStoryId(long id) throws Exception {
        Optional<StoryImage> image = storyImageRepository.findById(id);
        if (image.isPresent()) {
            return image.get().getImage();
        }
        throw new Exception("null image");
    }

    public void create(MultipartFile file, long id) throws IOException {
        if (!existsByStoryId(id))
            throw new NotFoundException(String.format("Row with id %s was not found", id));
        storyImageRepository.save(new StoryImage(id,  file.getBytes()));
    }

    @Transactional(transactionManager = "userTransactionManager")
    public void update(MultipartFile file, long id) throws IOException{
        // проверяем, существуют ли записи с таким идентификаторами
        if (!existsById(id))
            throw new NotFoundException(String.format("Row with id %s was not found", id));
        if (!existsByStoryId(id))
            throw new NotFoundException(String.format("Row with id %s was not found", id));
        storyImageRepository.save(new StoryImage(id,  file.getBytes()));
    }

    @Transactional(transactionManager = "userTransactionManager")
    public void delete(long id) {
        if (!existsById(id)) throw new NotFoundException(String.format("Entity with id %s not found", id));
        storyImageRepository.deleteById(id);
    }

    public boolean existsById(long id) {
        return storyImageRepository.existsById(id);
    }

    public boolean existsByStoryId(long id) {
        return storyRepository.existsById(id);
    }
}
