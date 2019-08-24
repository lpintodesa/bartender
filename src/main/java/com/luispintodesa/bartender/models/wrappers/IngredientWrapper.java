package com.luispintodesa.bartender.models.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luispintodesa.bartender.models.Ingredient;

import java.util.ArrayList;

public class IngredientWrapper {

    @JsonProperty("ingredients")
    private ArrayList<Ingredient> ingredient;

    public ArrayList<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(ArrayList<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public IngredientWrapper(ArrayList<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public IngredientWrapper() {
    }
}
