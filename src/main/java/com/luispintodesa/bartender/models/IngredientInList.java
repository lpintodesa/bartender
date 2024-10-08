package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientInList {

  @JsonProperty("strIngredient1")
  private String name;

  @Override
  public String toString() {
    return getName();
  }
}
