package com.overclock.overclock.model.enums;

public enum Program {

    Blander("Blander"),
    Adobe_Premiere("Adobe Premiere"),
    CS_GO("CS GO"),
    Metro_Exodus("Metro Exodus");

    private String fullName;

    Program(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
