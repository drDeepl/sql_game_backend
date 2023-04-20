package ru.sqlinvestigation.RestAPI.models.gameDB;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "educational_institution")
public class EducationalInstitution {
    @Id
    @Column(name = "edu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long edu_id;
    private String name;
    @NotNull
    private Long adress_id;

    public EducationalInstitution() {
    }

    public long getEdu_id() {
        return edu_id;
    }

    public void setEdu_id(long edu_id) {
        this.edu_id = edu_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAdress_id() {
        return adress_id;
    }

    public void setAdress_id(Long adress_id) {
        this.adress_id = adress_id;
    }
}
