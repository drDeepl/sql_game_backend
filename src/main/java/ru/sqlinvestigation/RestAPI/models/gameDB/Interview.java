package ru.sqlinvestigation.RestAPI.models.gameDB;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @Column(name = "interview_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long interview_id;
    private String transcript;
    @NotNull
    private Long person_id;
    @NotNull
    private Long CSR_id;

    public Interview() {
    }

    public long getInterview_id() {
        return interview_id;
    }

    public void setInterview_id(long interview_id) {
        this.interview_id = interview_id;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public Long getCSR_id() {
        return CSR_id;
    }

    public void setCSR_id(Long CSR_id) {
        this.CSR_id = CSR_id;
    }
}

