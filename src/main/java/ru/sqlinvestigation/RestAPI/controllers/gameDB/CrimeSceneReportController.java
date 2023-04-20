package ru.sqlinvestigation.RestAPI.controllers.gameDB;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.sqlinvestigation.RestAPI.models.gameDB.CrimeSceneReport;
import ru.sqlinvestigation.RestAPI.services.gameDB.CrimeSceneReportService;

import javax.validation.Valid;
import java.util.List;

@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("api/gameDB/crime_scene_report")
public class CrimeSceneReportController {
    private final CrimeSceneReportService reportService;

    public CrimeSceneReportController(CrimeSceneReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/index")
    public List<CrimeSceneReport> getDriverLicense() {
        return reportService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CrimeSceneReport report, BindingResult bindingResult) {
        reportService.create(report, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid CrimeSceneReport report, BindingResult bindingResult) {
        reportService.update(report, bindingResult);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDriverLicense(@PathVariable long id) {
        reportService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(exception.getMessage()));
    }
}