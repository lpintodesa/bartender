package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DrinkInList {

    @JsonProperty("idDrink")
    private int idDrink;

    @JsonProperty("strDrink")
    private String strDrink;

    @JsonProperty("strDrinkThumb")
    private String strDrinkThumb;


    // Getters and Setters

    public int getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(int idDrink) {
        this.idDrink = idDrink;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }
}