package com.overclock.overclock.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Assembly {

    @Positive
    private BigInteger id;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 23)
    private String name;

    @NotNull
    private final Motherboard motherboard;

    @NotNull
    private final CPU cpu;

    @NotNull
    private final GPU gpu;

    @NotNull
    private final RAM ram;

    @NotNull
    private final BigInteger author;

    @NotNull
    private final List<Comment> comments;

    @NotNull
    private final BigInteger overclock;

    @NotNull
    @Positive
    private BigInteger score;

    private Assembly(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.motherboard = builder.motherboard;
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.ram = builder.ram;
        this.author = builder.author;
        this.comments = builder.comments;
        this.overclock = builder.overclock;
        this.score = builder.score;
    }

    public static class Builder {
        private final BigInteger id;
        private final String name;
        private Motherboard motherboard;
        private CPU cpu;
        private GPU gpu;
        private RAM ram;
        private BigInteger author;
        private List<Comment> comments;
        private BigInteger overclock;
        private BigInteger score;

        public Builder(BigInteger id, String name) {
            this.id = id;
            this.name = name;
            this.comments = new ArrayList<>();
        }

        public Builder setMotherboard(Motherboard motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        public Builder setCpu(CPU cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setGpu(GPU gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder setRam(RAM ram) {
            this.ram = ram;
            return this;
        }

        public Builder setAuthor(BigInteger authorId) {
            this.author = authorId;
            return this;
        }

        public Builder setComments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public Builder setOverclock(BigInteger overclockId) {
            this.overclock = overclockId;
            return this;
        }

        public Builder setScore(BigInteger score) {
            this.score = score;
            return this;
        }

        public Assembly build() {
            return new Assembly(this);
        }
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public CPU getCpu() {
        return cpu;
    }

    public GPU getGpu() {
        return gpu;
    }

    public RAM getRam() {
        return ram;
    }

    public BigInteger getAuthor() {
        return author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public BigInteger getOverclock() {
        return overclock;
    }

    public BigInteger getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assembly assembly = (Assembly) o;
        return Objects.equals(getId(), assembly.getId()) &&
                Objects.equals(getName(), assembly.getName()) &&
                Objects.equals(getMotherboard(), assembly.getMotherboard()) &&
                Objects.equals(getCpu(), assembly.getCpu()) &&
                Objects.equals(getGpu(), assembly.getGpu()) &&
                Objects.equals(getRam(), assembly.getRam()) &&
                Objects.equals(getAuthor(), assembly.getAuthor()) &&
                Objects.equals(getComments(), assembly.getComments()) &&
                Objects.equals(getOverclock(), assembly.getOverclock()) &&
                Objects.equals(getScore(), assembly.getScore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getMotherboard(), getCpu(), getGpu(), getRam(), getAuthor(), getComments(), getOverclock(), getScore());
    }
}