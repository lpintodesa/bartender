package com.luispintodesa.bartender.models.forms;

import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.IngredientInList;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

@Data
public class MyBarForm {

  @NotNull private String ingredientName;

  public boolean checkIngredient(List<Ingredient> list) {

    return list.stream()
            .map(Ingredient::getName)
            .anyMatch(listIngredientName -> equalsIgnoreCase(ingredientName, listIngredientName));
  }

  public boolean checkIngredientInList(List<IngredientInList> list) {

    return list.stream()
            .map(IngredientInList::getName)
            .anyMatch(listIngredientName -> equalsIgnoreCase(ingredientName, listIngredientName));
  }
}
