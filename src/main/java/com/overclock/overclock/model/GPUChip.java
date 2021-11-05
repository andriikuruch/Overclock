package com.overclock.overclock.model;

public enum GPUChip {

    GeForce_GT_1030("GeForce GT 1030"),
    GeForce_GTX_1050("GeForce GTX 1050"),
    GeForce_GTX_1050_Ti("GeForce GTX 1050 Ti"),
    GeForce_GTX_1060("GeForce GTX 1060"),
    GeForce_GTX_1070("GeForce GTX 1070"),
    GeForce_GTX_1070_Ti("GeForce GTX 1070 Ti"),
    GeForce_GTX_1080("GeForce GTX 1080"),
    GeForce_GTX_1080_Ti("GeForce GTX 1080 Ti"),
    GeForce_GTX_1650("GeForce GTX 1650"),
    GeForce_GTX_1650_Super("GeForce GTX 1650 Super"),
    GeForce_GTX_1660("GeForce GTX 1660"),
    GeForce_GTX_1660_Super("GeForce GTX 1660 Super"),
    GeForce_GTX_1660_Ti("GeForce GTX 1660 Ti"),
    GeForce_RTX_2060("GeForce RTX 2060"),
    GeForce_RTX_2060_Super("GeForce RTX 2060 Super"),
    GeForce_RTX_2070("GeForce RTX 2070"),
    GeForce_RTX_2070_Super("GeForce RTX 2070 Super"),
    GeForce_RTX_2080("GeForce RTX 2080"),
    GeForce_RTX_2080_Super("GeForce RTX 2080 Super"),
    GeForce_RTX_2080_Ti("GeForce RTX 2080 Ti"),
    GeForce_RTX_3060("GeForce RTX 3060"),
    GeForce_RTX_3060_Ti("GeForce RTX 3060 Ti"),
    GeForce_RTX_3070("GeForce RTX 3070"),
    GeForce_RTX_3070_Ti("GeForce RTX 3070 Ti"),
    GeForce_RTX_3080("GeForce RTX 3080"),
    GeForce_RTX_3080_Ti("GeForce RTX 3080 Ti"),
    GeForce_RTX_3090("GeForce RTX 3090"),
    RX_550("RX 550"),
    RX_560("RX 560"),
    RX_570("RX 570"),
    RX_580("RX 580"),
    RX_590("RX 590"),
    RX_5500_XT("RX 5500 XT"),
    RX_5600_XT("RX 5600 XT"),
    RX_5700("RX 5700"),
    RX_5700_XT("RX 5700 XT"),
    RX_6600("RX 6600"),
    RX_6600_XT("RX 6600 XT"),
    RX_6700_XT("RX 6700 XT"),
    RX_6800("RX 6800"),
    RX_6800_XT("RX 6800 XT"),
    RX_6900_XT("RX 6900 XT");

    private String fullName;

    GPUChip(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
