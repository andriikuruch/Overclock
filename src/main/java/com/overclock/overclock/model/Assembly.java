package com.overclock.overclock.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class Assembly {

    @Positive
    private BigInteger id;

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

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 23)
    private String author;

    @NotNull
    private List<Comment> comments;

    @NotNull
    private Overclock overclock;

    @NotNull
    @Positive
    private BigInteger score;

    public static class Builder {

        private Assembly assembly;

        public Builder() {
            this.assembly = new Assembly();
        }

        public Builder setId(BigInteger id) {
            assembly.id = id;
            return this;
        }

        public Builder setName(String name) {
            assembly.name = name;
            return this;
        }

        public Builder setMotherboard(Motherboard motherboard) {
            assembly.motherboard = motherboard;
            return this;
        }

        public Builder setCpu(CPU cpu) {
            assembly.cpu = cpu;
            return this;
        }

        public Builder setGpu(GPU gpu) {
            assembly.gpu = gpu;
            return this;
        }

        public Builder setRam(RAM ram) {
            assembly.ram = ram;
            return this;
        }

        public Builder setAuthor(String author) {
            assembly.author = author;
            return this;
        }

        public Builder setComments(List<Comment> comments) {
            assembly.comments = comments;
            return this;
        }

        public Builder setOverclock(Overclock overclock) {
            assembly.overclock = overclock;
            return this;
        }

        public Builder setScore(BigInteger score) {
            assembly.score = score;
            return this;
        }

        public Assembly build() {
            return assembly;
        }
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Overclock getOverclock() {
        return overclock;
    }

    public void setOverclock(Overclock overclock) {
        this.overclock = overclock;
    }

    public BigInteger getScore() {
        return score;
    }

    public void setScore(BigInteger score) {
        this.score = score;
    }
}