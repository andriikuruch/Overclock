package com.overclock.overclock.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.overclock.overclock.model.enums.GPUChip;
import com.overclock.overclock.model.enums.GPUChipManufacturer;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@JsonDeserialize(builder = GPU.Builder.class)
public class GPU extends AbstractComponent {

    @NotNull
    private GPUChipManufacturer chipManufacturer;

    @NotNull
    private GPUChip chip;

    @NotNull
    @Max(value = 48)
    @Min(value = 1)
    private BigInteger memoryCapacity;

    @NotNull
    @Max(value = 2000)
    @Min(value = 1000)
    private BigInteger coreFrequency;

    @NotNull
    @Max(value = 23000)
    @Min(value = 1000)
    private BigInteger memoryFrequency;

    @NotNull
    @DecimalMax(value = "3.0")
    @DecimalMin(value = "0.5")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal voltage;

    private GPU(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.chipManufacturer = builder.chipManufacturer;
        this.chip = builder.chip;
        this.coreFrequency = builder.coreFrequency;
        this.memoryFrequency = builder.memoryFrequency;
        this.memoryCapacity = builder.memoryCapacity;
        this.voltage = builder.voltage;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {
        private final BigInteger id;
        private final String name;
        private GPUChipManufacturer chipManufacturer;
        private GPUChip chip;
        private BigInteger memoryCapacity;
        private BigInteger coreFrequency;
        private BigInteger memoryFrequency;
        private BigDecimal voltage;

        public Builder(BigInteger id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder setChipManufacturer(GPUChipManufacturer chipManufacturer) {
            this.chipManufacturer = chipManufacturer;
            return this;
        }

        public Builder setChip(GPUChip chip) {
            this.chip = chip;
            return this;
        }

        public Builder setMemoryCapacity(BigInteger memoryCapacity) {
            this.memoryCapacity = memoryCapacity;
            return this;
        }

        public Builder setCoreFrequency(BigInteger coreFrequency) {
            this.coreFrequency = coreFrequency;
            return this;
        }

        public Builder setMemoryFrequency(BigInteger memoryFrequency) {
            this.memoryFrequency = memoryFrequency;
            return this;
        }

        public Builder setVoltage(BigDecimal voltage) {
            this.voltage = voltage;
            return this;
        }

        public GPU build() {
            return new GPU(this);
        }
    }

    public GPUChipManufacturer getChipManufacturer() {
        return chipManufacturer;
    }

    public GPUChip getChip() {
        return chip;
    }

    public BigInteger getMemoryCapacity() {
        return memoryCapacity;
    }

    public BigInteger getCoreFrequency() {
        return coreFrequency;
    }

    public BigInteger getMemoryFrequency() {
        return memoryFrequency;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GPU gpu = (GPU) o;
        return getChipManufacturer() == gpu.getChipManufacturer() &&
                getChip() == gpu.getChip() &&
                Objects.equals(getMemoryCapacity(), gpu.getMemoryCapacity()) &&
                Objects.equals(getCoreFrequency(), gpu.getCoreFrequency()) &&
                Objects.equals(getMemoryFrequency(), gpu.getMemoryFrequency()) &&
                Objects.equals(getVoltage(), gpu.getVoltage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getChipManufacturer(), getChip(), getMemoryCapacity(), getCoreFrequency(), getMemoryFrequency(), getVoltage());
    }
}