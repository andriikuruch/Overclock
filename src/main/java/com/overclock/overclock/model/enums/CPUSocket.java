package com.overclock.overclock.model.enums;

public enum CPUSocket {
    Soc1151(33),
    Soc1200(34),
    AM4(35);

    private int id;

    CPUSocket(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }

    public static CPUSocket fromInt(int id) {
        if (Soc1200.toInt() == id)
            return Soc1200;

        if (AM4.toInt() == id)
            return AM4;

        return Soc1151;
    }
}
