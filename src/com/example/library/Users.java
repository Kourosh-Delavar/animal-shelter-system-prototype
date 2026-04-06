package com.example.library;

public class Users {
    enum AuthorizationLevels {
        caretaker,
        veteran,
        administrator,
        owner
    }

    public AuthorizationLevels authLevel;
    public String username;
    public String password;

}
