package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientInList {

  @JsonProperty("strIngredient1")
  private String name;

  public IngredientInList(String name) {
    this.name = name;
  }

  public IngredientInList() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return getName();
  }
}
