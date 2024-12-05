package ru.kata.spring.boot_security.demo.models;

import org.springframework.stereotype.Component;

@Component
public class Role {

    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
