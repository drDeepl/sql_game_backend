package ru.sqlinvestigation.RestAPI.models.gameDB;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "crime_scene_report")
public class CrimeSceneReport {
    @Id
    @Column(name = "CSR_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CSR_id;
    private String description;
    private String type;
    @NotNull
    private Long city_id;

    public CrimeSceneReport() {
    }

    public long getCSR_id() {
        return CSR_id;
    }

    public void setCSR_id(long CSR_id) {
        this.CSR_id = CSR_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }
}
