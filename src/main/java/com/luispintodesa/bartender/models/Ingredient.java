package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Ingredient {

    @JsonProperty("idIngredient")
    @Id
    private int id;

    @JsonProperty("strIngredient")
    private String strIngredient;

    @JsonProperty("strType")
    private String strType;

    @ManyToMany (mappedBy="ingredients")
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }

    @Override
    public String toString(){
        return getStrIngredient();
    }
}
