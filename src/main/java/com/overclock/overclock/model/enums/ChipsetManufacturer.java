package com.overclock.overclock.model.enums;

public enum ChipsetManufacturer {

    Intel("Intel"),
    AMD("AMD");

    private String fullName;

    ChipsetManufacturer(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
