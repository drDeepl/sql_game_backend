package ru.sqlinvestigation.RestAPI.controllers.gameDB;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.sqlinvestigation.RestAPI.dto.gameDB.DriverLicenseDTO;
import ru.sqlinvestigation.RestAPI.models.gameDB.DriverLicense;
import ru.sqlinvestigation.RestAPI.services.gameDB.DriverLicenseService;
import ru.sqlinvestigation.RestAPI.util.MapperUtil;
import ru.sqlinvestigation.RestAPI.util.PersonNotCreatedException;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("api/gameDB/driver_license")
public class DriverLicenseController {
    private final ModelMapper modelMapper;
    private final DriverLicenseService driverLicenseService;

    @Autowired
    public DriverLicenseController(ModelMapper modelMapper, DriverLicenseService driverLicenseService) {
        this.modelMapper = modelMapper;
        this.driverLicenseService = driverLicenseService;
    }

    @GetMapping("/index")
    public List<DriverLicenseDTO> getDriverLicense() {
        List<DriverLicense> driverLicenses = driverLicenseService.findAll();
        return MapperUtil.convertList(driverLicenses, this::convertToDriverLicenseDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid DriverLicense driverLicense,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = Collections.singletonList(bindingResult.getFieldError());
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        driverLicenseService.create(driverLicense);

        //Отправляем HTTP ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid DriverLicense driverLicense,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = Collections.singletonList(bindingResult.getFieldError());
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        // проверяем, существует ли запись с таким идентификатором
        if (!driverLicenseService.existsById(driverLicense)){
            return (ResponseEntity<HttpStatus>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
        driverLicenseService.create(driverLicense);

        //Отправляем HTTP ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDriverLicense(@PathVariable int id){
        driverLicenseService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    private DriverLicenseDTO convertToDriverLicenseDTO(DriverLicense driverLicense) {
        return modelMapper.map(driverLicense, DriverLicenseDTO.class);
    }
}
