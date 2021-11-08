package com.overclock.overclock.model;

import com.overclock.overclock.model.enums.GPUChip;
import com.overclock.overclock.model.enums.GPUChipManufacturer;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;

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

    public static class Builder {

        private GPU gpu;

        public Builder() {
            this.gpu = new GPU();
        }

        public Builder setId(BigInteger id) {
            gpu.id = id;
            return this;
        }

        public Builder setName(String name) {
            gpu.name = name;
            return this;
        }

        public Builder setChipManufacturer(GPUChipManufacturer chipManufacturer) {
            gpu.chipManufacturer = chipManufacturer;
            return this;
        }

        public Builder setChip(GPUChip chip) {
            gpu.chip = chip;
            return this;
        }

        public Builder setMemoryCapacity(BigInteger memoryCapacity) {
            gpu.memoryCapacity = memoryCapacity;
            return this;
        }

        public Builder setCoreFrequency(BigInteger coreFrequency) {
            gpu.coreFrequency = coreFrequency;
            return this;
        }

        public Builder setMemoryFrequency(BigInteger memoryFrequency) {
            gpu.memoryFrequency = memoryFrequency;
            return this;
        }

        public Builder setVoltage(BigDecimal voltage) {
            gpu.voltage = voltage;
            return this;
        }

        public GPU build() {
            return gpu;
        }
    }

    public GPUChipManufacturer getChipManufacturer() {
        return chipManufacturer;
    }

    public void setChipManufacturer(GPUChipManufacturer chipManufacturer) {
        this.chipManufacturer = chipManufacturer;
    }

    public GPUChip getChip() {
        return chip;
    }

    public void setChip(GPUChip chip) {
        this.chip = chip;
    }

    public BigInteger getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(BigInteger memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public BigInteger getCoreFrequency() {
        return coreFrequency;
    }

    public void setCoreFrequency(BigInteger coreFrequency) {
        this.coreFrequency = coreFrequency;
    }

    public BigInteger getMemoryFrequency() {
        return memoryFrequency;
    }

    public void setMemoryFrequency(BigInteger memoryFrequency) {
        this.memoryFrequency = memoryFrequency;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }
}