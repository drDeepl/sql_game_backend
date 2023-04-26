package ru.sqlinvestigation.RestAPI.services.userDB.JWT;


public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }

}
