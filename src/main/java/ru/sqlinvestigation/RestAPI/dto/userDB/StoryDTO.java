package ru.sqlinvestigation.RestAPI.dto.userDB;

public class StoryDTO {
    private long id;
    private String title;
    private String difficulty;
    private String description;
    private String story_text;

    public StoryDTO() {
    }

    public StoryDTO(long id, String title, String difficulty, String description, String story_text) {
    }

    public StoryDTO(long id, String title) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStory_text() {
        return story_text;
    }

    public void setStory_text(String story_text) {
        this.story_text = story_text;
    }
}
