package ru.obakumen.startup.dto;

import ru.obakumen.startup.models.User;

import javax.persistence.Column;

public class UserDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String roleName;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getRoleName() {
        return roleName;
    }
}
