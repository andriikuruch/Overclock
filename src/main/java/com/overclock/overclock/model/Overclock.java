package com.overclock.overclock.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class Overclock {

    @Positive
    private BigInteger id;

    @NotNull
    @DecimalMax(value = "8.0")
    @DecimalMin(value = "1.0")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal CPUFrequency;

    @NotNull
    @DecimalMax(value = "3.0")
    @DecimalMin(value = "0.5")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal CPUVoltage;

    @NotNull
    @Max(value = 2000)
    @Min(value = 1000)
    private BigInteger GPUCoreFrequency;

    @NotNull
    @Max(value = 23000)
    @Min(value = 1000)
    private BigInteger GPUMemoryFrequency;

    @NotNull
    @DecimalMax(value = "3.0")
    @DecimalMin(value = "0.5")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal GPUVoltage;

    @NotNull
    @DecimalMax(value = "3.0")
    @DecimalMin(value = "0.5")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal RAMVoltage;

    @NotNull
    @NotEmpty
    @Size(max = 23)
    private String RAMTimings;

    @NotNull
    @Max(value = 5000)
    @Min(value = 2133)
    private BigInteger RAMFrequency;

    public static class Builder {
        private Overclock overclock;

        public Builder() {
            this.overclock = new Overclock();
        }

        public Builder setId(BigInteger id) {
            overclock.id = id;
            return this;
        }

        public Builder setCPUFrequency(BigDecimal CPUFrequency) {
            overclock.CPUFrequency = CPUFrequency;
            return this;
        }

        public Builder setCPUVoltage(BigDecimal CPUVoltage) {
            overclock.CPUVoltage = CPUVoltage;
            return this;
        }

        public Builder setGPUCoreFrequency(BigInteger GPUCoreFrequency) {
            overclock.GPUCoreFrequency = GPUCoreFrequency;
            return this;
        }

        public Builder setGPUMemoryFrequency(BigInteger GPUMemoryFrequency) {
            overclock.GPUMemoryFrequency = GPUMemoryFrequency;
            return this;
        }

        public Builder setGPUVoltage(BigDecimal GPUVoltage) {
            overclock.GPUVoltage = GPUVoltage;
            return this;
        }

        public Builder setRAMVoltage(BigDecimal RAMVoltage) {
            overclock.RAMVoltage = RAMVoltage;
            return this;
        }

        public Builder setRAMTimings(String RAMTimings) {
            overclock.RAMTimings = RAMTimings;
            return this;
        }

        public Builder setRAMFrequency(BigInteger RAMFrequency) {
            overclock.RAMFrequency = RAMFrequency;
            return this;
        }

        public Overclock build() {
            return overclock;
        }
    }

    public BigInteger getId() {
        return id;
    }

    public BigDecimal getCPUFrequency() {
        return CPUFrequency;
    }

    public BigDecimal getCPUVoltage() {
        return CPUVoltage;
    }

    public BigInteger getGPUCoreFrequency() {
        return GPUCoreFrequency;
    }

    public BigInteger getGPUMemoryFrequency() {
        return GPUMemoryFrequency;
    }

    public BigDecimal getGPUVoltage() {
        return GPUVoltage;
    }

    public BigDecimal getRAMVoltage() {
        return RAMVoltage;
    }

    public String getRAMTimings() {
        return RAMTimings;
    }

    public BigInteger getRAMFrequency() {
        return RAMFrequency;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setCPUFrequency(BigDecimal CPUFrequency) {
        this.CPUFrequency = CPUFrequency;
    }

    public void setCPUVoltage(BigDecimal CPUVoltage) {
        this.CPUVoltage = CPUVoltage;
    }

    public void setGPUCoreFrequency(BigInteger GPUCoreFrequency) {
        this.GPUCoreFrequency = GPUCoreFrequency;
    }

    public void setGPUMemoryFrequency(BigInteger GPUMemoryFrequency) {
        this.GPUMemoryFrequency = GPUMemoryFrequency;
    }

    public void setGPUVoltage(BigDecimal GPUVoltage) {
        this.GPUVoltage = GPUVoltage;
    }

    public void setRAMVoltage(BigDecimal RAMVoltage) {
        this.RAMVoltage = RAMVoltage;
    }

    public void setRAMTimings(String RAMTimings) {
        this.RAMTimings = RAMTimings;
    }

    public void setRAMFrequency(BigInteger RAMFrequency) {
        this.RAMFrequency = RAMFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Overclock overclock = (Overclock) o;
        return Objects.equals(getId(), overclock.getId()) &&
                Objects.equals(getCPUFrequency(), overclock.getCPUFrequency()) &&
                Objects.equals(getCPUVoltage(), overclock.getCPUVoltage()) &&
                Objects.equals(getGPUCoreFrequency(), overclock.getGPUCoreFrequency()) &&
                Objects.equals(getGPUMemoryFrequency(), overclock.getGPUMemoryFrequency()) &&
                Objects.equals(getGPUVoltage(), overclock.getGPUVoltage()) &&
                Objects.equals(getRAMVoltage(), overclock.getRAMVoltage()) &&
                Objects.equals(getRAMTimings(), overclock.getRAMTimings()) &&
                Objects.equals(getRAMFrequency(), overclock.getRAMFrequency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCPUFrequency(), getCPUVoltage(), getGPUCoreFrequency(), getGPUMemoryFrequency(), getGPUVoltage(), getRAMVoltage(), getRAMTimings(), getRAMFrequency());
    }
}