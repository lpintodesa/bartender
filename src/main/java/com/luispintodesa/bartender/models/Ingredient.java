package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.stream.Collectors;

@Entity
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

  public Ingredient() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Drink> getDrinks() {
    return drinks;
  }

  public void setDrinks(List<Drink> drinks) {
    this.drinks = drinks;
  }

  public void addDrink(Drink drink) {
    this.drinks.add(drink);
  }

  public void removeDrink(Drink drink) {
    this.drinks.remove(drink);
  }

  public List<Integer> getDrinkIds() {
    return drinks.stream().map(Drink::getId).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return getName();
  }
}
