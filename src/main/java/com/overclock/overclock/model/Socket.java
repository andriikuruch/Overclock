package com.overclock.overclock.model;

public enum Socket {

    Soc1151("Socket 1151"),
    Soc1200("Socket 1200"),
    AM4("Socket AM4");

    private String fullName;

    Socket(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
