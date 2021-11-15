package com.overclock.overclock.model.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Chipset {
    H310(3),
    B360(4),
    Z390(5),
    H410(6),
    B460(7),
    Z490(8),
    H510(9),
    B560(10),
    Z590(11),
    A320(12),
    B350(13),
    X370(14),
    B450(15),
    X470(16),
    A520(17),
    B550(18),
    X570(19);

    private int id;
    private static Map<Integer, Chipset> map = Arrays.stream(Chipset.values())
            .collect(Collectors.toMap(Chipset::toInt, Function.identity()));

    Chipset(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }

    public static Chipset fromInt(int id) {
        return map.getOrDefault(id, null);
    }
}
