package com.luispintodesa.bartender.models.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luispintodesa.bartender.models.Drink;

import java.util.ArrayList;

public class DrinkWrapper {

    @JsonProperty("drinks")
    private ArrayList<Drink> drinks;

    private ArrayList<Integer> ids;

    //Getters and Setters

    public void setIds(ArrayList <Drink> drinks){
        for (Drink drink : drinks) {
            ids.add(drink.getIdDrink());
        }
    }

    public ArrayList<Integer> getIds() {return ids;}

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }

    //Constructors

    public DrinkWrapper() {
    }

    public DrinkWrapper(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }
}
