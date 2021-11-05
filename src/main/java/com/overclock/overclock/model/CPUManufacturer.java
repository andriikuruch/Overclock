package com.overclock.overclock.model;

public enum CPUManufacturer {

    Intel("Intel"),
    AMD("AMD");

    private String fullName;

    CPUManufacturer(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
