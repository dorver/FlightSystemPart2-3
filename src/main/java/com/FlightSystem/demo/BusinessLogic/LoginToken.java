package com.FlightSystem.demo.BusinessLogic;

public class LoginToken {
    public long id;
    public String name;
    String Role;

    public LoginToken(long id, String name, String role) {
        this.id = id;
        this.name = name;
        Role = role;
    }
}
