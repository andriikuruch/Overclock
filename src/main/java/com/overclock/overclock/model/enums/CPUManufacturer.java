package com.overclock.overclock.model.enums;

public enum CPUManufacturer {
    Intel(23),
    AMD(24);

    private int id;

    CPUManufacturer(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }

    public static CPUManufacturer fromInt(int id) {
        if (AMD.toInt() == id)
            return AMD;

        return Intel;
    }
}
