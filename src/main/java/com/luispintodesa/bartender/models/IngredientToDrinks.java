package com.luispintodesa.bartender.models;

import com.luispintodesa.bartender.models.manipulation.SpaceToUnderscoreConverter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IngredientToDrinks {

    private IngredientToDrinks() {
    }

    public static List<String> ingredientSearch(User theUser){
        List<String> urls = new ArrayList<>();

        for (Ingredient i:theUser.getIngredients()){
            urls.add("https://www.thecocktaildb.com/api/json/v2/" + Deserializer.getKey()+"/filter.php?i="+ SpaceToUnderscoreConverter.convert(i.getStrIngredient()));
        }
        return urls;
    }

    public static List<Integer> addDrinkIDsToList (List<String> urls){
        List<Integer> ids = new ArrayList<>();

        for (String i: urls){
            List<Integer> ids1 = (List<Integer>) Deserializer.searchDrinkBySingleIngredient(i);
            for (Integer id: ids1){
                if (!ids.contains(id)){
                    ids.add(id);
                }
            }
        }
        return ids;
    }

    public static List<Drink> idsToDrinks (List<Integer> ids){
        List<Drink> drinks = new ArrayList<>();

        for (Integer i:ids){

            drinks.add((Drink) Deserializer.searchDrinkById(i));
        }
        return drinks;
    }

    public static void setMatchCounter (List<Drink> drinks, User theUser){

        for (Drink drink:drinks){
            int matchCounter = 0;

            HashSet<String> strIngredientsHashSet = new HashSet<>();

            String[] strIngredients = {drink.getStrIngredient1(), drink.getStrIngredient2(),drink.getStrIngredient3(),drink.getStrIngredient4(),
                    drink.getStrIngredient5(),drink.getStrIngredient6(),drink.getStrIngredient7(),drink.getStrIngredient8(),drink.getStrIngredient9(),
                    drink.getStrIngredient10(),drink.getStrIngredient11(),drink.getStrIngredient12(),drink.getStrIngredient13(),
                    drink.getStrIngredient14(),drink.getStrIngredient15()};

            for (String string:strIngredients) {
                if (string != null && !string.equals("")) {
                    strIngredientsHashSet.add(string.toLowerCase());
                }
            }
            
            for (Ingredient ingredient:theUser.getIngredients()){
                if (ingredient.getStrIngredient()!=null && strIngredientsHashSet.contains(ingredient.getStrIngredient().toLowerCase())){
                            matchCounter+=1;
                }
            }

            drink.setScore(strIngredientsHashSet.size()-matchCounter);
        }
    }
}
