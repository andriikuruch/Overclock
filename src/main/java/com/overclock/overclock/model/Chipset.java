package com.overclock.overclock.model;

public enum Chipset {

    H310("H310"),
    B360("B360"),
    Z390("Z390"),
    H410("H410"),
    B460("B460"),
    Z490("Z490"),
    H510("H510"),
    B560("B560"),
    Z590("Z590"),
    A320("A320"),
    B350("B350"),
    X370("X370"),
    B450("B450"),
    X470("X470"),
    A520("A520"),
    B550("B550"),
    X570("X570");

    private String fullName;

    Chipset(String fullName) {
        this.fullName = fullName;
    }

    String getFullName() {
        return fullName;
    }
}
