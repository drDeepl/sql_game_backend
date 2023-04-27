package ru.sqlinvestigation.RestAPI.models.userDB;

import javax.persistence.*;

@Entity
@Table(name = "stories_images")
public class StoryImage {
    @Id
    @Column(name = "id_stories")
    private long id_stories;

    @Lob
    @Column(name = "image")
    private byte[] image;

    public StoryImage() {
    }

    public StoryImage(long id_stories, byte[] image) {
        this.id_stories = id_stories;
        this.image = image;
    }

    public long getId_stories() {
        return id_stories;
    }

    public void setId_stories(long id_stories) {
        this.id_stories = id_stories;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
