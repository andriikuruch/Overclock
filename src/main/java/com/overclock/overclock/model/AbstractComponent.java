package com.overclock.overclock.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigInteger;

abstract class AbstractComponent {

    @Positive
    protected BigInteger id;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 23)
    protected String name;

    public String getName() {
        return name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
