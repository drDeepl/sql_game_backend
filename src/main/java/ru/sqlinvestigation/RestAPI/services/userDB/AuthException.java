package ru.sqlinvestigation.RestAPI.services.userDB;


public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }

}
