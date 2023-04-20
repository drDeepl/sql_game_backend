package ru.sqlinvestigation.RestAPI.models.gameDB;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "students")
public class Students {
    @Id
    @Column(name = "students_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long students_id;
    @NotNull
    private Long edu_id;
    @NotNull
    private Long person_id;
    private String enrollment_date;
    private String expulsion_date;

    public Students() {
    }

    public long getStudents_id() {
        return students_id;
    }

    public void setStudents_id(long students_id) {
        this.students_id = students_id;
    }

    public Long getEdu_id() {
        return edu_id;
    }

    public void setEdu_id(Long edu_id) {
        this.edu_id = edu_id;
    }

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public String getEnrollment_date() {
        return enrollment_date;
    }

    public void setEnrollment_date(String enrollment_date) {
        this.enrollment_date = enrollment_date;
    }

    public String getExpulsion_date() {
        return expulsion_date;
    }

    public void setExpulsion_date(String expulsion_date) {
        this.expulsion_date = expulsion_date;
    }
}

