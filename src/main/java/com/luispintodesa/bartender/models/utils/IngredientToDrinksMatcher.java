package com.luispintodesa.bartender.models.utils;

import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.User;
import org.apache.commons.collections4.CollectionUtils;

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

  public static void updateIngredientsToDrinksMatch (User theUser) {

    for (Ingredient ingredient : theUser.getIngredients()) {
      List<Integer> drinkIds = DeserializerUtils.searchDrinkIdsBySingleIngredient(ingredient);
      List<Integer> newIds =
              (List<Integer>) CollectionUtils.removeAll(drinkIds, ingredient.getDrinkIds());

      for (Drink drink : ingredient.getDrinks()) {
        if (!drinkIds.contains(drink.getId())) {
          ingredient.removeDrink(drink);
        }
      }

      for (Integer id : drinkIds) {
        if (newIds.contains(id)) {
          Drink newDrink = DeserializerUtils.searchDrinkById(id);
          drinkDao.save(newDrink);
          ingredient.addDrink(newDrink);
        }
      }
      ingredientDao.save(ingredient);
    }
  }
}
