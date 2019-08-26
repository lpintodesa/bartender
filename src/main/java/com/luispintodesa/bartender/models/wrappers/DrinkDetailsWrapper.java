package com.luispintodesa.bartender.models.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luispintodesa.bartender.models.DrinkDetails;

import java.util.ArrayList;

public class DrinkDetailsWrapper {

    @JsonProperty("drinks")
    private ArrayList<DrinkDetails> drinks;

    //Getters and Setters

    public ArrayList<DrinkDetails> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<DrinkDetails> drinks) {
        this.drinks = drinks;
    }

    //Constructors

    public DrinkDetailsWrapper() {
    }

    public DrinkDetailsWrapper(ArrayList<DrinkDetails> drinks) {
        this.drinks = drinks;
    }
}
