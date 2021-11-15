package com.overclock.overclock.model.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum GPUChip {
    GeForce_GT_1030(46),
    GeForce_GTX_1050(47),
    GeForce_GTX_1050_Ti(48),
    GeForce_GTX_1060(49),
    GeForce_GTX_1070(50),
    GeForce_GTX_1070_Ti(51),
    GeForce_GTX_1080(52),
    GeForce_GTX_1080_Ti(53),
    GeForce_GTX_1650(54),
    GeForce_GTX_1650_Super(55),
    GeForce_GTX_1660(56),
    GeForce_GTX_1660_Super(57),
    GeForce_GTX_1660_Ti(58),
    GeForce_RTX_2060(59),
    GeForce_RTX_2060_Super(60),
    GeForce_RTX_2070(61),
    GeForce_RTX_2070_Super(62),
    GeForce_RTX_2080(63),
    GeForce_RTX_2080_Super(64),
    GeForce_RTX_2080_Ti(65),
    GeForce_RTX_3060(66),
    GeForce_RTX_3060_Ti(67),
    GeForce_RTX_3070(68),
    GeForce_RTX_3070_Ti(69),
    GeForce_RTX_3080(70),
    GeForce_RTX_3080_Ti(71),
    GeForce_RTX_3090(72),
    RX_550(73),
    RX_560(74),
    RX_570(75),
    RX_590(76),
    RX_5500_XT(77),
    RX_5600_XT(78),
    RX_5700(79),
    RX_5700_XT(80),
    RX_6600(81),
    RX_6600_XT(82),
    RX_6700_XT(83),
    RX_6800(84),
    RX_6800_XT(85),
    RX_6900_XT(86);

    private int id;
    private static Map<Integer, GPUChip> map = Arrays.stream(GPUChip.values())
            .collect(Collectors.toMap(GPUChip::toInt, Function.identity()));

    GPUChip(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }

    public static GPUChip fromInt(int id) {
        return map.getOrDefault(id, null);
    }
}
