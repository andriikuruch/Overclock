package com.overclock.overclock.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;

public class RAM extends AbstractComponent {

    @NotNull
    @Max(value = 256)
    @Min(value = 1)
    private BigInteger capacity;

    @NotNull
    @Max(value = 5000)
    @Min(value = 2133)
    private BigInteger frequency;

    @NotNull
    @NotEmpty
    @Size(max = 23)
    private String timings;

    @NotNull
    @DecimalMax(value = "3.0")
    @DecimalMin(value = "0.5")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal voltage;

    public static class Builder {

        private RAM ram;

        public Builder() {
            this.ram = new RAM();
        }

        public Builder setId(BigInteger id) {
            ram.id = id;
            return this;
        }

        public Builder setName(String name) {
            ram.name = name;
            return this;
        }

        public Builder setCapacity(BigInteger capacity) {
            ram.capacity = capacity;
            return this;
        }

        public Builder setFrequency(BigInteger frequency) {
            ram.frequency = frequency;
            return this;
        }

        public Builder setTimings(String timings) {
            ram.timings = timings;
            return this;
        }

        public Builder setVoltage(BigDecimal voltage) {
            ram.voltage = voltage;
            return this;
        }

        public RAM build() {
            return ram;
        }
    }

    public BigInteger getCapacity() {
        return capacity;
    }

    public void setCapacity(BigInteger capacity) {
        this.capacity = capacity;
    }

    public BigInteger getFrequency() {
        return frequency;
    }

    public void setFrequency(BigInteger frequency) {
        this.frequency = frequency;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }
}