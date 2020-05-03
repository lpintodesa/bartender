package com.luispintodesa.bartender.models.utils;

import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IngredientToDrinksMatcher {

  private IngredientToDrinksMatcher() {}

  public static List<Drink> matchUserIngredientsToDrinks(User theUser, int intendedScore) {
    List<Integer> ids = new ArrayList<>();
    Set<String> strUserIngredientHashSet = new HashSet<>();
    List<Drink> drinks = new ArrayList<>();

    for (Ingredient ingredient : theUser.getIngredients()) {
      strUserIngredientHashSet.add(ingredient.getName());
    }

    for (Ingredient ingredient : theUser.getIngredients()) {
      List<Drink> singleIngredientDrinks =
          DeserializerUtils.searchDrinkBySingleIngredient(ingredient);
      for (Drink drink : singleIngredientDrinks) {
        int id = drink.getId();
        if (!ids.contains(id)) {
          ids.add(id);
          Drink drinkById = DeserializerUtils.searchDrinkById(id);
          int score = setMatchCounter(drinkById, strUserIngredientHashSet);
          if (score == intendedScore) {
            drinks.add(drinkById);
          }
        }
      }
    }
    return drinks;
  }

  public static Integer setMatchCounter(Drink drink, Set<String> userIngredientsSet) {

    List<String> strIngredients =
        Arrays.asList(
            drink.getNameIngredient1(),
            drink.getNameIngredient2(),
            drink.getNameIngredient3(),
            drink.getNameIngredient4(),
            drink.getNameIngredient5(),
            drink.getNameIngredient6(),
            drink.getNameIngredient7(),
            drink.getNameIngredient8(),
            drink.getNameIngredient9(),
            drink.getNameIngredient10(),
            drink.getNameIngredient11(),
            drink.getNameIngredient12(),
            drink.getNameIngredient13(),
            drink.getNameIngredient14(),
            drink.getNameIngredient15());

    HashSet<String> strDrinkIngredientsHashSet = new HashSet<>(strIngredients);

    strDrinkIngredientsHashSet.remove(null);

    strDrinkIngredientsHashSet.removeAll(userIngredientsSet);

    return strDrinkIngredientsHashSet.size();
  }
}
