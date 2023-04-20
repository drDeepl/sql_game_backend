package ru.sqlinvestigation.RestAPI.models.gameDB;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "driver_license")
public class DriverLicense {
    @Id
    @Column(name = "license_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long license_id;
    @NotNull
    private Long person_id;
    private String plate_number;
    private String car_make;
    private String car_model;

    public DriverLicense() {
    }

    public DriverLicense(long license_id, Person person, String plate_number, String car_make, String car_model, Long person_id) {
        this.license_id = license_id;
        this.plate_number = plate_number;
        this.car_make = car_make;
        this.car_model = car_model;
        this.person_id = person_id;
    }

    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(long person_id) {
        this.person_id = person_id;
    }


    public long getLicense_id() {
        return license_id;
    }

    public void setLicense_id(Long license_id) {
        this.license_id = license_id;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getCar_make() {
        return car_make;
    }

    public void setCar_make(String car_make) {
        this.car_make = car_make;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }
}
