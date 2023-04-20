package ru.sqlinvestigation.RestAPI.controllers.gameDB;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.EducationalInstitution;
import ru.sqlinvestigation.RestAPI.services.gameDB.EDUService;

import javax.validation.Valid;
import java.util.List;

@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("api/gameDB/educational_institution")
public class EDUController {
    private final EDUService eduService;

    public EDUController(EDUService eduService) {
        this.eduService = eduService;
    }

    @GetMapping("/index")
    public List<EducationalInstitution> getDriverLicense() {
        return eduService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid EducationalInstitution edu, BindingResult bindingResult) {
        eduService.create(edu, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid EducationalInstitution edu, BindingResult bindingResult) {
        eduService.update(edu, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDriverLicense(@PathVariable long id) {
        eduService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
    }
}