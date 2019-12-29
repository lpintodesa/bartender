package com.luispintodesa.bartender.models.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luispintodesa.bartender.models.IngredientInList;

import java.util.List;

public class IngredientInListWrapper {

    @JsonProperty("drinks")

    private List<IngredientInList> drinks;

    //Getter and Setter

    public List<IngredientInList> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<IngredientInList> drinks) {
        this.drinks = drinks;
    }

    //Constructors

    public IngredientInListWrapper(List<IngredientInList> drinks) {
        this.drinks = drinks;
    }

    public IngredientInListWrapper() {
    }
}
