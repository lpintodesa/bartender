package com.luispintodesa.bartender.models.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luispintodesa.bartender.models.DrinkID;

import java.util.ArrayList;

public class DrinkIDWrapper {

    @JsonProperty("drinks")
    private ArrayList<DrinkID> drinks;

    //Getter and Setter


    public ArrayList<DrinkID> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<DrinkID> drinks) {
        this.drinks = drinks;
    }

    //Constructors

    public DrinkIDWrapper() {
    }

    public DrinkIDWrapper(ArrayList<DrinkID> drinks) {
        this.drinks = drinks;
    }
}
