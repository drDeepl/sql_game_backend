package ru.sqlinvestigation.RestAPI.models.gameDB;

import javax.persistence.*;

@Entity
@Table(name = "driver_license")
public class DriverLicense {
    @Id
    @Column(name = "license_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long license_id;

    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;
    private String plate_number;
    private String car_make;
    private String car_model;

    public DriverLicense() {
    }

    public long getLicense_id() {
        return license_id;
    }

    public void setLicense_id(long license_id) {
        this.license_id = license_id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
