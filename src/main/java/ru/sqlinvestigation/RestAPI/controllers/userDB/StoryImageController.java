package ru.sqlinvestigation.RestAPI.controllers.userDB;

import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.userDB.StoryImage;
import ru.sqlinvestigation.RestAPI.services.userDB.StoryImageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/userDB/stories_images")
public class StoryImageController {
    private final StoryImageService storyImageService;

    @Autowired
    public StoryImageController(StoryImageService storyImageService) {
        this.storyImageService = storyImageService;
    }

    @GetMapping("/listID")
    public List<Long> findListId(){
        return storyImageService.findListId();
    }

    @GetMapping("/findByStoryId/{id}")
    public String findByStoryId(@PathVariable long id) throws Exception {
        return storyImageService.findByStoryId(id);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid StoryImage storyImage) {
        storyImageService.create(storyImage);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid StoryImage storyImage) {
        storyImageService.update(storyImage);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
        storyImageService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(ex.getMessage()));
    }
}