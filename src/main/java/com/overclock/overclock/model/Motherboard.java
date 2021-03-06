package com.overclock.overclock.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.overclock.overclock.model.enums.Chipset;
import com.overclock.overclock.model.enums.ChipsetManufacturer;
import com.overclock.overclock.model.enums.MotherboardSocket;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Objects;

@JsonDeserialize(builder = Motherboard.Builder.class)
public class Motherboard extends AbstractComponent {

    @NotNull
    private MotherboardSocket socket;

    @NotNull
    private ChipsetManufacturer chipsetManufacturer;

    @NotNull
    private Chipset chipset;

    private Motherboard(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.chipset = builder.chipset;
        this.chipsetManufacturer = builder.chipsetManufacturer;
        this.socket = builder.socket;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        private final BigInteger id;
        private final String name;
        private MotherboardSocket socket;
        private ChipsetManufacturer chipsetManufacturer;
        private Chipset chipset;

        public Builder(BigInteger id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder setSocket(MotherboardSocket socket) {
            this.socket = socket;
            return this;
        }

        public Builder setChipsetManufacturer(ChipsetManufacturer chipsetManufacturer) {
            this.chipsetManufacturer = chipsetManufacturer;
            return this;
        }

        public Builder setChipset(Chipset chipset) {
            this.chipset = chipset;
            return this;
        }

        public Motherboard build() {
            return new Motherboard(this);
        }
    }

    public MotherboardSocket getSocket() {
        return socket;
    }

    public Chipset getChipset() {
        return chipset;
    }

    public ChipsetManufacturer getChipsetManufacturer() {
        return chipsetManufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Motherboard that = (Motherboard) o;
        return getSocket() == that.getSocket() &&
                getChipsetManufacturer() == that.getChipsetManufacturer() &&
                getChipset() == that.getChipset();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSocket(), getChipsetManufacturer(), getChipset());
    }
}