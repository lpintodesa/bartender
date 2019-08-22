package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

public class ListIngredient {

    @JsonProperty("strIngredient1")
    private String strIngredient1;

    public ListIngredient(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public ListIngredient(){

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
