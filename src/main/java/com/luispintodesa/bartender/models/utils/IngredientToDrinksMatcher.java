package com.luispintodesa.bartender.models.utils;

import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class IngredientToDrinksMatcher {

  private IngredientToDrinksMatcher() {}

  public static List<Drink> matchUserIngredientsToDrinks(User theUser, int intendedScore) {

    return theUser.getIngredients().stream()
        .map(Ingredient::getDrinks)
        .flatMap(Collection::stream)
        .distinct()
        .filter(
            drink ->
                setMatchCounter(
                        drink.getLowerCaseIngredientNames(), theUser.getLowerCaseIngredientNames())
                    == intendedScore)
        .collect(Collectors.toList());
  }

  public static int setMatchCounter(
      List<String> drinkIngredients, List<String> userIngredientsNames) {

    drinkIngredients.removeAll(userIngredientsNames);

    return drinkIngredients.size();
  }
}
