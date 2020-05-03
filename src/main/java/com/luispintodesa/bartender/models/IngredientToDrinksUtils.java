package com.luispintodesa.bartender.models;

import com.luispintodesa.bartender.models.manipulation.SpaceToUnderscoreConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IngredientToDrinksUtils {

    public static List<Drink> userIngredientsToDrinks(User theUser, int intendedScore){
        List<Integer> ids = new ArrayList<>();
        Set<String> strUserIngredientHashSet = new HashSet<>();
        List<Drink> drinks = new ArrayList<>();

        for (Ingredient ingredient:theUser.getIngredients()){
            strUserIngredientHashSet.add(ingredient.getStrIngredient());
        }

        for (Ingredient ingredient:theUser.getIngredients()){
            List<Drink> singleIngredientDrinks = DeserializerUtils.searchDrinks(makeSearchDrinkBySingleIngredientURL(ingredient));
            for (Drink drink: singleIngredientDrinks){
                int id = drink.getIdDrink();
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

    public static String makeSearchDrinkBySingleIngredientURL (Ingredient ingredient){
        return "https://www.thecocktaildb.com/api/json/v2/" + DeserializerUtils.getKey()+"/filter.php?i="+ SpaceToUnderscoreConverter.convert(ingredient.getStrIngredient());
    }

    public static Integer setMatchCounter (Drink drink, Set<String> userIngredientsSet){

        List<String> strIngredients = Arrays.asList(drink.getStrIngredient1(), drink.getStrIngredient2(),drink.getStrIngredient3(),drink.getStrIngredient4(),
                    drink.getStrIngredient5(),drink.getStrIngredient6(),drink.getStrIngredient7(),drink.getStrIngredient8(),drink.getStrIngredient9(),
                    drink.getStrIngredient10(),drink.getStrIngredient11(),drink.getStrIngredient12(),drink.getStrIngredient13(),
                    drink.getStrIngredient14(),drink.getStrIngredient15());

        HashSet<String> strDrinkIngredientsHashSet = new HashSet<>(strIngredients);

        strDrinkIngredientsHashSet.remove(null);

        strDrinkIngredientsHashSet.removeAll(userIngredientsSet);

        return strDrinkIngredientsHashSet.size();
        }

    private IngredientToDrinksUtils() {
    }
}
