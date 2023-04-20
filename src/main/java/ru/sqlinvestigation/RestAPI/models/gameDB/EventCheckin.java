package ru.sqlinvestigation.RestAPI.models.gameDB;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "event_checkin")
public class EventCheckin {
    @Id
    @Column(name = "EC_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long EC_id;
    private String event_name;
    @NotNull
    private Long person_id;
    private String date;

    public EventCheckin() {
    }

    public long getEC_id() {
        return EC_id;
    }

    public void setEC_id(long EC_id) {
        this.EC_id = EC_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
