package com.luispintodesa.bartender.models.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luispintodesa.bartender.models.Drink;

import java.util.List;

public class DrinkWrapper {

    @JsonProperty("drinks")
    private List<Drink> drinks;

    //Getters and Setters

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    //Constructors

    public DrinkWrapper() {
    }

    public DrinkWrapper(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
