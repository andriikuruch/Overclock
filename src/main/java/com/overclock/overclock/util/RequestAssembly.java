package com.overclock.overclock.util;

import com.overclock.overclock.model.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestAssembly {
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 23)
    private String name;

    @NotNull
    private Motherboard motherboard;

    @NotNull
    private CPU cpu;

    @NotNull
    private GPU gpu;

    @NotNull
    private RAM ram;

    public static class Builder {

        private RequestAssembly requestAssembly;

        public Builder() {
            this.requestAssembly = new RequestAssembly();
        }

        public Builder setName(String name) {
            requestAssembly.name = name;
            return this;
        }

        public Builder setMotherboard(Motherboard motherboard) {
            requestAssembly.motherboard = motherboard;
            return this;
        }

        public Builder setCpu(CPU cpu) {
            requestAssembly.cpu = cpu;
            return this;
        }

        public Builder setGpu(GPU gpu) {
            requestAssembly.gpu = gpu;
            return this;
        }

        public Builder setRam(RAM ram) {
            requestAssembly.ram = ram;
            return this;
        }

        public RequestAssembly build() {
            return requestAssembly;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public GPU getGpu() {
        return gpu;
    }

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }
}
