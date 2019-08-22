package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class IngredientListWrapper {

    @JsonProperty("drinks")
    private ArrayList<ListIngredient> drinks;

    public ArrayList<ListIngredient> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<ListIngredient> drinks) {
        this.drinks = drinks;
    }

    public IngredientListWrapper(ArrayList<ListIngredient> drinks) {
        this.drinks = drinks;
    }

    public IngredientListWrapper() {
    }
}
