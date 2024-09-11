package com.seguranca_info.demo.models;

public enum Role {
    USER("USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
