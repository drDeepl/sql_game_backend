//package ru.sqlinvestigation.RestAPI.models.gameDB;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//import javax.validation.constraints.Null;
//import javax.validation.constraints.Size;
//
//@Entity
//@Table(name = "income")
//public class Income {
//    @Id
//    @Column(name = "ssn")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long ssn;
//
//    @Column(name = "annual_income")
//    private int annualIncome;
//
//    public long getSsn() {
//        return ssn;
//    }
//
//    public void setSsn(long ssn) {
//        this.ssn = ssn;
//    }
//
//    public int getAnnualIncome() {
//        return annualIncome;
//    }
//
//    public void setAnnualIncome(int annualIncome) {
//        this.annualIncome = annualIncome;
//    }
////    @JsonIgnore
////    @OneToOne(mappedBy = "income")
////    private Person person;
////
////    public Person getPerson() {
////        return person;
////    }
////
////    public void setPerson(Person person) {
////        this.person = person;
////    }
//}
