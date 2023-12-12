package com.example.algorithms;

import java.io.Serializable;

public class  User implements Serializable {
    String firstName;
    String lastName;
    String login;
    String password;

    User(String firstName, String lastName, String login, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", login = '" + login + '\'' +
                ", password = '" + password + '\'' +
                '}';
    }
}
