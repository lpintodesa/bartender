package com.luispintodesa.bartender.models.forms;

import javax.validation.constraints.NotNull;

public class MyBarForm {

    @NotNull
    private String ingredientName;

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

}
