package com.overclock.overclock.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;

public class CPU extends AbstractComponent {

    @NotNull
    private Socket socket;

    @NotNull
    private CPUManufacturer manufacturer;

    @NotNull
    private CPUGeneration generation;

    @NotNull
    @DecimalMax(value = "3.0")
    @DecimalMin(value = "0.5")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal voltage;

    @NotNull
    @DecimalMax(value = "8.0")
    @DecimalMin(value = "1.0")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal frequency;

    @NotNull
    @Max(value = 128)
    @Min(value = 0)
    private BigInteger threadsNumber;

    @NotNull
    @Max(value = 64)
    @Min(value = 1)
    private BigInteger coresNumber;

    @NotNull
    private CPUFamily family;

    public static class Builder {

        private CPU cpu;

        public Builder() {
            this.cpu = new CPU();
        }

        public Builder setId(BigInteger id) {
            cpu.id = id;
            return this;
        }

        public Builder setName(String name) {
            cpu.name = name;
            return this;
        }

        public Builder setSocket(Socket socket) {
            cpu.socket = socket;
            return this;
        }

        public Builder setManufacturer(CPUManufacturer manufacturer) {
            cpu.manufacturer = manufacturer;
            return this;
        }

        public Builder setGeneration(CPUGeneration generation) {
            cpu.generation = generation;
            return this;
        }

        public Builder setVoltage(BigDecimal voltage) {
            cpu.voltage = voltage;
            return this;
        }

        public Builder setFrequency(BigDecimal frequency) {
            cpu.frequency = frequency;
            return this;
        }

        public Builder setThreadsNumber(BigInteger threadsNumber) {
            cpu.threadsNumber = threadsNumber;
            return this;
        }

        public Builder setCoresNumber(BigInteger coresNumber) {
            cpu.coresNumber = coresNumber;
            return this;
        }

        public Builder setFamily(CPUFamily family) {
            cpu.family = family;
            return this;
        }

        public CPU build() {
            return cpu;
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public CPUManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(CPUManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public CPUGeneration getGeneration() {
        return generation;
    }

    public void setGeneration(CPUGeneration generation) {
        this.generation = generation;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }

    public BigDecimal getFrequency() {
        return frequency;
    }

    public void setFrequency(BigDecimal frequency) {
        this.frequency = frequency;
    }

    public BigInteger getThreadsNumber() {
        return threadsNumber;
    }

    public void setThreadsNumber(BigInteger threadsNumber) {
        this.threadsNumber = threadsNumber;
    }

    public BigInteger getCoresNumber() {
        return coresNumber;
    }

    public void setCoresNumber(BigInteger coresNumber) {
        this.coresNumber = coresNumber;
    }

    public CPUFamily getFamily() {
        return family;
    }

    public void setFamily(CPUFamily family) {
        this.family = family;
    }
}