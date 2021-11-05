package com.overclock.overclock.model;

public enum CPUFamily {

    Core_i3("Intel Core i3"),
    Core_i5("Intel Core i5"),
    Core_i7("Intel Core i7"),
    Core_i9("Intel Core i9"),
    Ryzen3("AMD Ryzen 3"),
    Ryzen5("AMD Ryzen 5"),
    Ryzen7("AMD Ryzen 7"),
    Ryzen9("AMD Ryzen 9");

    private String fullName;

    CPUFamily(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
