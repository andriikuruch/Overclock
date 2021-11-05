package com.overclock.overclock.model;

public enum CPUGeneration {

    EightGen("8 поколение (Coffee Lake)"),
    NineGen("9 поколение (Coffee Lake Refresh)"),
    TenGen("10 поколение (Comet Lake)"),
    ElevenGen("11 поколение (Rocket Lake)"),
    Zen("Zen"),
    ZenPlus("Zen+"),
    ZenTwo("Zen 2"),
    ZenThree("Zen 3");

    private String fullName;

    CPUGeneration(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
