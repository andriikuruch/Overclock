package com.overclock.overclock.model.enums;

public enum Role {
    admin("Admin"),
    user("User");

    private String fullName;

    Role(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
