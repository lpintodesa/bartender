package com.luispintodesa.bartender.models.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luispintodesa.bartender.models.Ingredient;

import java.util.List;

public class IngredientWrapper {

    @JsonProperty("ingredients")
    private List<Ingredient> ingredient;

    public List<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public IngredientWrapper(List<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public IngredientWrapper() {
    }
}
