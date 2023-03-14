package ru.sqlinvestigation.RestAPI.config;

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
public class DatabaseProperty {

    private String url;
    private String username;
    private String password;
    private String classDriver;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassDriver() {
        return classDriver;
    }

    public void setClassDriver(String classDriver) {
        this.classDriver = classDriver;
    }
}
