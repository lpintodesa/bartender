package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Ingredient {

  @JsonProperty("idIngredient")
  @Id
  private int id;

  @JsonProperty("strIngredient")
  private String name;

  @JsonProperty("strType")
  private String type;

  @ManyToMany(mappedBy = "ingredients")
  private List<User> users;

  @ManyToMany private List<Drink> drinks;

  public void addDrink(Drink drink) {
    this.drinks.add(drink);
  }

  public void removeDrink(Drink drink) {
    this.drinks.remove(drink);
  }

  public List<Integer> getDrinkIds() {
    return drinks
            .stream()
            .map(Drink::getId)
            .toList();
  }

  @Override
  public String toString() {
    return getName();
  }
}
