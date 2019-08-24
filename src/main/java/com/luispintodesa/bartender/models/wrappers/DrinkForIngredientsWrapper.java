package com.luispintodesa.bartender.models.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luispintodesa.bartender.models.DrinkForIngredients;
import com.luispintodesa.bartender.models.DrinkID;

import java.util.ArrayList;

public class DrinkForIngredientsWrapper {

    @JsonProperty("drinks")
    private ArrayList<DrinkForIngredients> drinks;

    //Getters and Setters

    public ArrayList<DrinkForIngredients> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<DrinkForIngredients> drinks) {
        this.drinks = drinks;
    }

    //Constructors

    public DrinkForIngredientsWrapper(ArrayList<DrinkForIngredients> drinks) {
        this.drinks = drinks;
    }

    public DrinkForIngredientsWrapper() {
    }
}
