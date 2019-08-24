package com.luispintodesa.bartender.models.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luispintodesa.bartender.models.ListIngredient;

import java.util.ArrayList;

public class IngredientListWrapper {

    @JsonProperty("drinks")

    private ArrayList<ListIngredient> drinks;

    //Getter and Setter

    public ArrayList<ListIngredient> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<ListIngredient> drinks) {
        this.drinks = drinks;
    }

    //Constructors

    public IngredientListWrapper(ArrayList<ListIngredient> drinks) {
        this.drinks = drinks;
    }

    public IngredientListWrapper() {
    }
}
