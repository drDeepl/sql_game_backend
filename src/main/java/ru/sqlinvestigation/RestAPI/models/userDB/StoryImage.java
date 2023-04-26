package ru.sqlinvestigation.RestAPI.models.userDB;

import javax.persistence.*;

@Entity
@Table(name = "stories_images")
public class StoryImage {
    @Id
    @Column(name = "id_stories")
    private long id_stories;

    @Column(name = "image")
    private String image;

    public StoryImage() {
    }

    public long getId_stories() {
        return id_stories;
    }

    public void setId_stories(long id_stories) {
        this.id_stories = id_stories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
