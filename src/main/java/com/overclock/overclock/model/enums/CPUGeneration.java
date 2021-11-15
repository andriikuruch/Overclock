package com.overclock.overclock.model.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CPUGeneration {
    EightGen(36),
    NineGen(37),
    TenGen(38),
    ElevenGen(39),
    Zen(40),
    ZenPlus(41),
    ZenTwo(42),
    ZenThree(43);

    private int id;
    private static Map<Integer, CPUGeneration> map = Arrays.stream(CPUGeneration.values())
            .collect(Collectors.toMap(CPUGeneration::toInt, Function.identity()));

    CPUGeneration(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }

    public static CPUGeneration fromInt(int id) {
        return map.getOrDefault(id, null);
    }
}
