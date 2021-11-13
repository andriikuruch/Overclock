package com.overclock.overclock.model;

import com.overclock.overclock.model.enums.Chipset;
import com.overclock.overclock.model.enums.ChipsetManufacturer;
import com.overclock.overclock.model.enums.Socket;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Objects;

public class Motherboard extends AbstractComponent {

    @NotNull
    private Socket socket;

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

    public static class Builder {

        private final BigInteger id;
        private final String name;
        private Socket socket;
        private ChipsetManufacturer chipsetManufacturer;
        private Chipset chipset;

        public Builder(BigInteger id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder setSocket(Socket socket) {
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

    public Socket getSocket() {
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