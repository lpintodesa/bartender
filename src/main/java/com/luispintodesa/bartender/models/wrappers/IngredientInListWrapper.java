package com.luispintodesa.bartender.models.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luispintodesa.bartender.models.IngredientInList;

import java.util.ArrayList;

public class IngredientInListWrapper {

    @JsonProperty("drinks")

    private ArrayList<IngredientInList> drinks;

    //Getter and Setter

    public ArrayList<IngredientInList> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<IngredientInList> drinks) {
        this.drinks = drinks;
    }

    //Constructors

    public IngredientInListWrapper(ArrayList<IngredientInList> drinks) {
        this.drinks = drinks;
    }

    public IngredientInListWrapper() {
    }
}
