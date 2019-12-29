package com.luispintodesa.bartender.models.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class WhatCanIMakeForm {

    @Size(min=2, max=15)
    @NotNull
    private String cocktailName;

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }
}
