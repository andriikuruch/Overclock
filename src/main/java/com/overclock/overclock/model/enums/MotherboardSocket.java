package com.overclock.overclock.model.enums;

public enum MotherboardSocket {
    Soc1151(20),
    Soc1200(21),
    AM4(22);

    private int id;

    MotherboardSocket(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }

    public static MotherboardSocket fromInt(int id) {
        if (Soc1151.toInt() == id)
            return Soc1151;

        if (Soc1200.toInt() == id)
            return Soc1200;

        if (AM4.toInt() == id)
            return AM4;

        return null;
    }
}
