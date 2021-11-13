package com.overclock.overclock.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

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

    private RAM(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.capacity = builder.capacity;
        this.frequency = builder.frequency;
        this.timings = builder.timings;
        this.voltage = builder.voltage;
    }

    public static class Builder {
        private final BigInteger id;
        private final String name;
        private BigInteger capacity;
        private BigInteger frequency;
        private String timings;
        private BigDecimal voltage;

        public Builder(BigInteger id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder setCapacity(BigInteger capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder setFrequency(BigInteger frequency) {
            this.frequency = frequency;
            return this;
        }

        public Builder setTimings(String timings) {
            this.timings = timings;
            return this;
        }

        public Builder setVoltage(BigDecimal voltage) {
            this.voltage = voltage;
            return this;
        }

        public RAM build() {
            return new RAM(this);
        }
    }

    public BigInteger getCapacity() {
        return capacity;
    }

    public BigInteger getFrequency() {
        return frequency;
    }

    public String getTimings() {
        return timings;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RAM ram = (RAM) o;
        return Objects.equals(getCapacity(), ram.getCapacity()) &&
                Objects.equals(getFrequency(), ram.getFrequency()) &&
                Objects.equals(getTimings(), ram.getTimings()) &&
                Objects.equals(getVoltage(), ram.getVoltage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCapacity(), getFrequency(), getTimings(), getVoltage());
    }
}