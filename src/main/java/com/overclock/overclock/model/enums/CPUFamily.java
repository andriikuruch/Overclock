package com.overclock.overclock.model.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CPUFamily {
    Core_i3(25),
    Core_i5(26),
    Core_i7(27),
    Core_i9(28),
    Ryzen3(29),
    Ryzen5(30),
    Ryzen7(31),
    Ryzen9(32);

    private int id;
    private static Map<Integer, CPUFamily> map = Arrays.stream(CPUFamily.values())
            .collect(Collectors.toMap(CPUFamily::toInt, Function.identity()));

    CPUFamily(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }

    public static CPUFamily fromInt(int id) {
        return map.getOrDefault(id, null);
    }
}
