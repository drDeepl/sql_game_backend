package ru.sqlinvestigation.RestAPI.models.userDB;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user_stats_by_stories")
public class UserStats {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "story_id")
    private long story_id;
    @Basic
    @Column(name = "user_id")
    private long user_id;
    @Basic
    @Column(name = "game_end_date")
    private Date game_end_date;
    @Basic
    @Column(name = "checks_answer")
    private int checks_answer;
    @Basic
    @Column(name = "scores")
    private int scores;
    @Basic
    @Column(name = "game_end_time_in_sec")
    private int game_end_time_in_sec;

    public UserStats() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStory_id() {
        return story_id;
    }

    public void setStory_id(int story_id) {
        this.story_id = story_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getGame_end_date() {
        return game_end_date;
    }

    public void setGame_end_date(Date game_end_date) {
        this.game_end_date = game_end_date;
    }

    public int getChecks_answer() {
        return checks_answer;
    }

    public void setChecks_answer(int checks_answer) {
        this.checks_answer = checks_answer;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public int getGame_end_time_in_sec() {
        return game_end_time_in_sec;
    }

    public void setGame_end_time_in_sec(int game_end_time_in_sec) {
        this.game_end_time_in_sec = game_end_time_in_sec;
    }
}
