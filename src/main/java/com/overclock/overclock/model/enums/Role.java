package com.overclock.overclock.model.enums;

public enum Role {
    ADMIN(88),
    USER(87);

    private int id;

    Role(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }

    public static Role fromInt(int id) {
        if (ADMIN.toInt() == id)
            return ADMIN;

        return USER;
    }
}
