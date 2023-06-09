package ru.sqlinvestigation.RestAPI.controllers.userDB;

import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.userDB.UserStats;
import ru.sqlinvestigation.RestAPI.services.userDB.UserStatsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/userDB/user_stats_by_stories")
public class UserStatsController {
    private final UserStatsService userStatsService;

    @Autowired
    public UserStatsController(UserStatsService userStatsService) {
        this.userStatsService = userStatsService;
    }

    @GetMapping("/index")
    public List<UserStats> getDriverLicense() {
        return userStatsService.findAll();
    }

    @GetMapping("/findByUserId/{id}")
    public List<UserStats> findByUserId(@PathVariable long id) throws Exception {
        return userStatsService.findByUserId(id);
    }

    @GetMapping("/findByStoryId/{id}")
    public List<UserStats> findByStoryId(@PathVariable long id) {
        return userStatsService.findAllByStoryId(id);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserStats userStats, BindingResult bindingResult) {
        userStatsService.create(userStats, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid UserStats userStats, BindingResult bindingResult) {
        userStatsService.update(userStats, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
        userStatsService.delete(id);
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