package ru.sqlinvestigation.RestAPI.controllers.gameDB;

import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.EventCheckin;
import ru.sqlinvestigation.RestAPI.services.gameDB.EventCheckinService;

import javax.validation.Valid;
import java.util.List;

@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("api/gameDB/event_checkin")
public class EventCheckinController {
    private final EventCheckinService eventCheckinService;

    @Autowired
    public EventCheckinController(EventCheckinService eventCheckinService) {
        this.eventCheckinService = eventCheckinService;
    }

    @GetMapping("/index")
    public List<EventCheckin> getDriverLicense() {
        return eventCheckinService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid EventCheckin eventCheckin, BindingResult bindingResult) {
        eventCheckinService.create(eventCheckin, bindingResult);
        //Отправляем HTTP ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid EventCheckin eventCheckin, BindingResult bindingResult) {
        eventCheckinService.update(eventCheckin, bindingResult);
        //Отправляем HTTP ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDriverLicense(@PathVariable long id) {
        eventCheckinService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
    }
}
