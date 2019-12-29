package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientInList {

    @JsonProperty("strIngredient1")
    private String strIngredient1;

    public IngredientInList(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public IngredientInList(){

    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    @Override
    public String toString(){
        return getStrIngredient1();
    }
}
