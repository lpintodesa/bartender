package com.luispintodesa.bartender.models.forms;

import javax.validation.constraints.NotNull;

public class WhatCanIMakeForm {

    @NotNull
    private String cocktailName;

    public WhatCanIMakeForm(){}

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }
}
