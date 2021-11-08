package com.overclock.overclock.model;

import com.overclock.overclock.model.enums.Chipset;
import com.overclock.overclock.model.enums.ChipsetManufacturer;
import com.overclock.overclock.model.enums.Socket;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class Motherboard extends AbstractComponent {

    @NotNull
    private Socket socket;

    @NotNull
    private ChipsetManufacturer chipsetManufacturer;

    @NotNull
    private Chipset chipset;

    public static class Builder {

        private Motherboard motherboard;

        public Builder() {
            this.motherboard = new Motherboard();
        }

        public Builder setId(BigInteger id) {
            motherboard.id = id;
            return this;
        }

        public Builder setName(String name) {
            motherboard.name = name;
            return this;
        }

        public Builder setSocket(Socket socket) {
            motherboard.socket = socket;
            return this;
        }

        public Builder setChipsetManufacturer(ChipsetManufacturer chipsetManufacturer) {
            motherboard.chipsetManufacturer = chipsetManufacturer;
            return this;
        }

        public Builder setChipset(Chipset chipset) {
            motherboard.chipset = chipset;
            return this;
        }

        public Motherboard build() {
            return motherboard;
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

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setChipsetManufacturer(ChipsetManufacturer chipsetManufacturer) {
        this.chipsetManufacturer = chipsetManufacturer;
    }

    public void setChipset(Chipset chipset) {
        this.chipset = chipset;
    }
}