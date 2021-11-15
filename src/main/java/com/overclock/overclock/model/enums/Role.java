package com.overclock.overclock.model.enums;

public enum Role {
    ADMIN(90),
    USER(89);

    private int id;

    Role(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }

    public static Role fromInt(int id) {
        if (USER.toInt() == id)
            return USER;

        if (ADMIN.toInt() == id)
            return ADMIN;

        return null;
    }
}
