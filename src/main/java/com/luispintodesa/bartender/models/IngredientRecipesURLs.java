package com.luispintodesa.bartender.models;

import com.luispintodesa.bartender.models.jsontopojos.DrinkForIngredientsJSONtoPOJOs;
import com.luispintodesa.bartender.models.jsontopojos.DrinkIDJSONtoPOJOs;
import com.luispintodesa.bartender.models.manipulation.SpaceToUnderscore;

import java.util.ArrayList;

public class IngredientRecipesURLs {

    public static ArrayList<String> ingredient_search(User theUser){
        ArrayList<String> urls = new ArrayList<String>();

        for (Ingredient i:theUser.getIngredients()){
            urls.add("https://www.thecocktaildb.com/api/json/v1/1/filter.php?i="+ SpaceToUnderscore.convert(i.getStrIngredient()));
        }
        return urls;
    }

    public static ArrayList<Integer> addDrinkIDsToList (ArrayList<String> urls){
        ArrayList<Integer> ids = new ArrayList<Integer>();

        for (String i: urls){
            ArrayList<Integer> ids1 = (ArrayList<Integer>) DrinkIDJSONtoPOJOs.convert(i);
            for (Integer id: ids1){
                if (!ids.contains(id)){
                    ids.add(id);
                }
            }
        }
        return ids;
    }

    public static ArrayList<DrinkForIngredients> idsToDrinks (ArrayList<Integer> ids){
        ArrayList<DrinkForIngredients> drinks = new ArrayList<DrinkForIngredients>();

        for (Integer i:ids){

            drinks.add((DrinkForIngredients)DrinkForIngredientsJSONtoPOJOs.convert(i));
        }
        return drinks;
    }

    public static void setMatchCounter (ArrayList<DrinkForIngredients> drinks, User theUser){

        for (DrinkForIngredients drink:drinks){
            int ingredient_counter = 0;
            int match_counter = 0;

            String[] strIngredients = {drink.getStrIngredient1(), drink.getStrIngredient2(),drink.getStrIngredient3(),drink.getStrIngredient4(),
                    drink.getStrIngredient5(),drink.getStrIngredient6(),drink.getStrIngredient7(),drink.getStrIngredient8(),drink.getStrIngredient9(),
                    drink.getStrIngredient10(),drink.getStrIngredient11(),drink.getStrIngredient12(),drink.getStrIngredient13(),
                    drink.getStrIngredient14(),drink.getStrIngredient15()};

            for (String string:strIngredients){
                if (string!=null && !string.equals("")){
                    ingredient_counter+=1;
                }
                for (Ingredient ingredient:theUser.getIngredients()){

                    if (ingredient.getStrIngredient().equals(string)){
                        match_counter+=1;
                    }
                }
            }
            drink.setScore(ingredient_counter-match_counter);
        }
    }
}
