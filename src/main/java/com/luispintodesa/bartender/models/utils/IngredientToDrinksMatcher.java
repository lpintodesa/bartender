package com.luispintodesa.bartender.models.utils;

import com.google.common.collect.Sets;
import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.Sets.newHashSet;

public class IngredientToDrinksMatcher {

  private IngredientToDrinksMatcher() {}

  public static List<Drink> matchUserIngredientsToDrinks(User theUser, int intendedScore) {

    Set<String> strUserIngredientHashSet = new HashSet<>();

    for (Ingredient ingredient : theUser.getIngredients()) {
      strUserIngredientHashSet.add(ingredient.getName());
    }

    return theUser.getIngredients()
            .stream()
            .map(DeserializerUtils::searchDrinkIdsBySingleIngredient)
            .flatMap(Collection::stream)
            .distinct()
            .map(DeserializerUtils::searchDrinkById)
            .filter(drink -> setMatchCounter(drink,strUserIngredientHashSet)==intendedScore)
            .collect(Collectors.toList());
  }

  public static int setMatchCounter(Drink drink, Set<String> userIngredientsSet) {

    HashSet<String> strDrinkIngredientsHashSet = newHashSet(
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

    strDrinkIngredientsHashSet.remove(null);

    return Sets.difference(strDrinkIngredientsHashSet,userIngredientsSet).size();
  }
}
