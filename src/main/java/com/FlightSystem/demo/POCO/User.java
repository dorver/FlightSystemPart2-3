package com.FlightSystem.demo.POCO;

public class User implements POCO{
    public long id;
    public String userName;
    public String password;
    public String email;
    public int userRole;

    public User(long id, String userName, String password, String email, int userRole) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
