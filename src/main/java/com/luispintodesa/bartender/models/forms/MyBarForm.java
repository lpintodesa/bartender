package com.luispintodesa.bartender.models.forms;

import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.IngredientInList;

import javax.validation.constraints.NotNull;
import java.util.List;

public class MyBarForm {

  @NotNull private String ingredientName;

  public boolean checkIngredient(List<Ingredient> list) {

    for (Ingredient i : list) {
      if (i.getName().equalsIgnoreCase(ingredientName)) {
        return true;
      }
    }
    return false;
  }

  public boolean checkIngredientInList(List<IngredientInList> list) {

    for (IngredientInList i : list) {
      if (i.getName() != null && i.getName().equalsIgnoreCase(ingredientName)) {
        return true;
      }
    }
    return false;
  }

  public String getIngredientName() {
    return ingredientName;
  }

  public void setIngredientName(String ingredientName) {
    this.ingredientName = ingredientName;
  }
}
