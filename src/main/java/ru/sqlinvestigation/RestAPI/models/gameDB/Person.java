package ru.sqlinvestigation.RestAPI.models.gameDB;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    //@NotEmpty(message = "Name should not be empty")
    //@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters !")
    private String name;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "license_id", referencedColumnName = "id")
    private DriverLicense driverLicense;
    @Column(name = "address_number")
    //@NotEmpty(message = "Email should be not empty")
    private int addressNumber;

    @Column(name = "address_street_name")
    //@NotEmpty(message = "Email should be not empty")
    private String addressStreetName;

    @OneToOne
    @JoinColumn(name = "ssn", referencedColumnName = "ssn")
    private Income income;

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressStreetName() {
        return addressStreetName;
    }

    public void setAddressStreetName(String addressStreetName) {
        this.addressStreetName = addressStreetName;
    }


    public DriverLicense getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(DriverLicense driverLicense) {
        this.driverLicense = driverLicense;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person() {

    }
}
