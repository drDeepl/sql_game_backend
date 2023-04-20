package ru.sqlinvestigation.RestAPI.controllers.gameDB;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.Workers;
import ru.sqlinvestigation.RestAPI.services.gameDB.WorkersService;

import javax.validation.Valid;
import java.util.List;

@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("api/gameDB/workers")
public class WorkersController {
    private final WorkersService workersService;

    public WorkersController(WorkersService workersService) {
        this.workersService = workersService;
    }

    @GetMapping("/index")
    public List<Workers> getDriverLicense() {
        return workersService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Workers workers, BindingResult bindingResult) {
        workersService.create(workers, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid Workers workers, BindingResult bindingResult) {
        workersService.update(workers, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDriverLicense(@PathVariable long id) {
        workersService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
    }
}