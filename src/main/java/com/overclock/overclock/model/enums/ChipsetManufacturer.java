package com.overclock.overclock.model.enums;

public enum ChipsetManufacturer {
    Intel(1),
    AMD(2);

    private int id;

    ChipsetManufacturer(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }

    public static ChipsetManufacturer fromInt(int id) {
        if (AMD.toInt() == id)
            return AMD;

        return Intel;
    }
}
