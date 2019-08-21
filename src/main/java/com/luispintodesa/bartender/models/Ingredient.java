package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

public class Ingredient {

    @JsonProperty("strIngredient1")
    private String strIngredient1;

    public Ingredient(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public Ingredient (){

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
