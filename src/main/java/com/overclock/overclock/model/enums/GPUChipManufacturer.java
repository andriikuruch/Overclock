package com.overclock.overclock.model.enums;

public enum GPUChipManufacturer {
    Nvidia(44),
    AMD(45);

    private int id;

    GPUChipManufacturer(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }

    public static GPUChipManufacturer fromInt(int id) {
        if (AMD.toInt() == id)
            return AMD;

        return Nvidia;
    }
}
