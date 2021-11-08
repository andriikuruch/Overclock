package com.overclock.overclock.model.enums;

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
