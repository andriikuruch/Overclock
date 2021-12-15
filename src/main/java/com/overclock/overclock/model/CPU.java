package com.overclock.overclock.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.overclock.overclock.model.enums.CPUFamily;
import com.overclock.overclock.model.enums.CPUGeneration;
import com.overclock.overclock.model.enums.CPUManufacturer;
import com.overclock.overclock.model.enums.CPUSocket;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@JsonDeserialize(builder = CPU.Builder.class)
public class CPU extends AbstractComponent {

    @NotNull
    private CPUSocket socket;

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

    private CPU(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.coresNumber = builder.coresNumber;
        this.family = builder.family;
        this.frequency = builder.frequency;
        this.generation = builder.generation;
        this.manufacturer = builder.manufacturer;
        this.voltage = builder.voltage;
        this.threadsNumber = builder.threadsNumber;
        this.socket = builder.socket;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private final BigInteger id;
        private String name;
        private CPUSocket socket;
        private CPUManufacturer manufacturer;
        private CPUGeneration generation;
        private BigDecimal voltage;
        private BigDecimal frequency;
        private BigInteger threadsNumber;
        private BigInteger coresNumber;
        private CPUFamily family;

        public Builder(BigInteger id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder setSocket(CPUSocket socket) {
            this.socket = socket;
            return this;
        }

        public Builder setManufacturer(CPUManufacturer manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder setGeneration(CPUGeneration generation) {
            this.generation = generation;
            return this;
        }

        public Builder setVoltage(BigDecimal voltage) {
            this.voltage = voltage;
            return this;
        }

        public Builder setFrequency(BigDecimal frequency) {
            this.frequency = frequency;
            return this;
        }

        public Builder setThreadsNumber(BigInteger threadsNumber) {
            this.threadsNumber = threadsNumber;
            return this;
        }

        public Builder setCoresNumber(BigInteger coresNumber) {
            this.coresNumber = coresNumber;
            return this;
        }

        public Builder setFamily(CPUFamily family) {
            this.family = family;
            return this;
        }

        public CPU build() {
            return new CPU(this);
        }
    }

    public CPUSocket getSocket() {
        return socket;
    }

    public CPUManufacturer getManufacturer() {
        return manufacturer;
    }

    public CPUGeneration getGeneration() {
        return generation;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public BigDecimal getFrequency() {
        return frequency;
    }

    public BigInteger getThreadsNumber() {
        return threadsNumber;
    }

    public BigInteger getCoresNumber() {
        return coresNumber;
    }

    public CPUFamily getFamily() {
        return family;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CPU cpu = (CPU) o;
        return getSocket() == cpu.getSocket() &&
                getManufacturer() == cpu.getManufacturer() &&
                getGeneration() == cpu.getGeneration() &&
                Objects.equals(getVoltage(), cpu.getVoltage()) &&
                Objects.equals(getFrequency(), cpu.getFrequency()) &&
                Objects.equals(getThreadsNumber(), cpu.getThreadsNumber()) &&
                Objects.equals(getCoresNumber(), cpu.getCoresNumber()) &&
                getFamily() == cpu.getFamily();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSocket(), getManufacturer(), getGeneration(), getVoltage(), getFrequency(), getThreadsNumber(), getCoresNumber(), getFamily());
    }
}