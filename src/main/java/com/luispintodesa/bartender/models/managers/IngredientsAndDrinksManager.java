package com.luispintodesa.bartender.models;

import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.User;
import com.luispintodesa.bartender.models.dao.DrinkDao;
import com.luispintodesa.bartender.models.dao.IngredientDao;
import com.luispintodesa.bartender.models.utils.DeserializerUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IngredientsAndDrinksManager {

  @Autowired protected IngredientDao ingredientDao;
  @Autowired protected DrinkDao drinkDao;

  public Map<Integer, List<Drink>> matchUserIngredientsToDrinks(User theUser) {

    List<Drink> allDrinks = theUser.getIngredients().stream()
            .map(Ingredient::getDrinks)
            .flatMap(Collection::stream)
            .distinct()
            .collect(Collectors.toList());

    for (Drink drink : allDrinks){
      drink.setMissingIngredients(removeMatchedIngredients(drink.getTitleCaseIngredientNames(),theUser.getLowerCaseIngredientNames()));
    }

    return allDrinks.stream().collect(Collectors.groupingBy(drink -> drink.getMissingIngredients().size()));
  }

  public List<String> removeMatchedIngredients(
      List<String> drinkIngredientNames, List<String> userIngredientsNames) {

    drinkIngredientNames.removeAll(userIngredientsNames);

    return drinkIngredientNames;
  }

  public void updateDrinksForAllIngredients(User theUser){
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

  public void addNewDrinksForNewIngredient (Ingredient newIngredient){
    List<Drink> drinks =
            DeserializerUtils.searchDrinkIdsBySingleIngredient(newIngredient).stream()
                    .map(
                            id ->
                                    (drinkDao.existsById(id)
                                            ? (drinkDao.findById((int)id))
                                            : DeserializerUtils.searchDrinkById(id)))
                    .collect(Collectors.toList());

    drinkDao.saveAll(drinks);

    newIngredient.setDrinks(drinks);
    ingredientDao.save(newIngredient);
  }

  public void saveDrink(Drink drink){
    drinkDao.save(drink);
  }

}
