package com.overclock.overclock.model.enums;

public enum GPUChipManufacturer {

    Nvidia("Nvidia"),
    AMD("AMD");

    private String fullName;

    GPUChipManufacturer(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}