package com.luispintodesa.bartender.models.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luispintodesa.bartender.models.DrinkInList;

import java.util.ArrayList;

public class DrinkInListWrapper {

    @JsonProperty("drinks")
    private ArrayList<DrinkInList> drinks;

    public ArrayList<DrinkInList> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<DrinkInList> drinks) {
        this.drinks = drinks;
    }

    public DrinkInListWrapper(ArrayList<DrinkInList> drinks) {
        this.drinks = drinks;
    }

    public DrinkInListWrapper() {
    }
}
