package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class IngredientWrapper {

    @JsonProperty("drinks")
    private ArrayList<Ingredient> drinks;

    public ArrayList<Ingredient> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Ingredient> drinks) {
        this.drinks = drinks;
    }

    public IngredientWrapper(ArrayList<Ingredient> drinks) {
        this.drinks = drinks;
    }

    public IngredientWrapper() {
    }
}
