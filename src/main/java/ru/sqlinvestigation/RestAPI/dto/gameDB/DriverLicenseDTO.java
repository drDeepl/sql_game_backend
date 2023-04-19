package ru.sqlinvestigation.RestAPI.dto.gameDB;

import ru.sqlinvestigation.RestAPI.models.gameDB.Person;

import javax.persistence.*;

public class DriverLicenseDTO {
    private long license_id;
    private long person_id;
    private Person person;
    private String plate_number;
    private String car_make;
    private String car_model;
}
