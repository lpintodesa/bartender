package com.luispintodesa.bartender.models.managers;

import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.dao.DrinkDao;
import com.luispintodesa.bartender.models.dao.IngredientDao;
import com.luispintodesa.bartender.models.utils.DeserializerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.luispintodesa.bartender.models.utils.DeserializerUtils.searchDrinkById;
import static com.luispintodesa.bartender.models.utils.DeserializerUtils.searchDrinkIdsBySingleIngredient;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.collections4.ListUtils.removeAll;
import static org.apache.commons.collections4.ListUtils.union;

@Service
@RequiredArgsConstructor
public class IngredientsAndDrinksManager {

  private final IngredientDao ingredientDao;
  private final DrinkDao drinkDao;

  public Map<Integer, List<Drink>> matchUserIngredientsToDrinks(User theUser) {

    List<Drink> allDrinks = theUser.getIngredients().stream()
                              .map(Ingredient::getDrinks)
                              .flatMap(Collection::stream)
                              .distinct()
                              .toList();

    for (Drink drink : allDrinks) {
      var missingIngredients = removeAll(drink.getTitleCaseIngredientNames(), theUser.getLowerCaseIngredientNames());
      drink.setMissingIngredients(missingIngredients);
    }

    return allDrinks
            .stream()
            .collect(groupingBy(drink -> drink.getMissingIngredients().size()));
  }

  public void updateDrinksForAllIngredients(User theUser) {
    for (Ingredient ingredient : theUser.getIngredients()) {
      List<Integer> drinkIds = searchDrinkIdsBySingleIngredient(ingredient);

      var oldDrinks = ingredient.getDrinks()
                        .stream()
                        .filter(drink -> drinkIds.contains(drink.getId()))
                        .toList();

     var newDrinks = removeAll(drinkIds, ingredient.getDrinkIds())
                      .stream()
                      .map(DeserializerUtils::searchDrinkById)
                      .toList();

      drinkDao.saveAll(newDrinks);

      ingredient.setDrinks(union(oldDrinks, newDrinks));
      ingredientDao.save(ingredient);
    }
  }

  public void addNewDrinksForNewIngredient(Ingredient newIngredient) {
    List<Drink> drinks = searchDrinkIdsBySingleIngredient(newIngredient).stream()
                          .map(id -> drinkDao.existsById(id)
                                      ? (drinkDao.findById((int) id))
                                      : searchDrinkById(id))
                          .toList();

    drinkDao.saveAll(drinks);
    newIngredient.setDrinks(drinks);
    ingredientDao.save(newIngredient);
  }

  public void saveDrink(Drink drink) {
    drinkDao.save(drink);
  }
}
