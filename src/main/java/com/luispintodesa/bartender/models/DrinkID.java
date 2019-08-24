package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

import java.net.URL;

public class DrinkID {

    @JsonProperty("strDrinkThumb")
    private Object strDrinkThumb;

    @JsonProperty("idDrink")
    private int idDrink;

    //Getters and Setters


    public Object getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(Object strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    public int getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(int idDrink) {
        this.idDrink = idDrink;
    }
}
