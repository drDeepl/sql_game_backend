package ru.sqlinvestigation.RestAPI.models.gameDB;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "workers")
public class Workers {
    @Id
    @Column(name = "workers_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long workers_id;
    @NotNull
    private Long person_id;
    @NotNull
    private Long organization_id;

    public Workers() {
    }

    public long getWorkers_id() {
        return workers_id;
    }

    public void setWorkers_id(long workers_id) {
        this.workers_id = workers_id;
    }

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public Long getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Long organization_id) {
        this.organization_id = organization_id;
    }
}

