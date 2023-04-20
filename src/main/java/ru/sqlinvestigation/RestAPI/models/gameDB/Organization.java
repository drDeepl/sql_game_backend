package ru.sqlinvestigation.RestAPI.models.gameDB;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @Column(name = "organization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long organization_id;
    private String name;
    @NotNull
    private Long address_id;

    public Organization() {
    }

    public long getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(long organization_id) {
        this.organization_id = organization_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }
}

