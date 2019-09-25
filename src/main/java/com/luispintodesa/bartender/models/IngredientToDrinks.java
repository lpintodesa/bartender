package com.luispintodesa.bartender.models;

import com.luispintodesa.bartender.models.manipulation.SpaceToUnderscoreConverter;

import java.util.ArrayList;

public class IngredientToDrinks {

    public static ArrayList<String> ingredient_search(User theUser){
        ArrayList<String> urls = new ArrayList<String>();

        for (Ingredient i:theUser.getIngredients()){
            urls.add("https://www.thecocktaildb.com/api/json/v1/1/filter.php?i="+ SpaceToUnderscoreConverter.convert(i.getStrIngredient()));
        }
        return urls;
    }

    public static ArrayList<Integer> addDrinkIDsToList (ArrayList<String> urls) {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        for (String i : urls) {
            ArrayList<Integer> ids1 = (ArrayList<Integer>) Deserializer.searchDrinkBySingleIngredient(i);
                for (Integer id : ids1) {
                    if (!ids.contains(id)) {
                        ids.add(id);
                    }
                }

        }
        return ids;
    }

    public static ArrayList<Drink> idsToDrinks (ArrayList<Integer> ids){
        ArrayList<Drink> drinks = new ArrayList<Drink>();

        for (Integer i:ids){

            drinks.add((Drink) Deserializer.searchDrinkById(i));
        }
        return drinks;
    }

    public static void setMatchCounter (ArrayList<Drink> drinks, User theUser){

        for (Drink drink:drinks){
            int ingredient_counter = 0;
            int match_counter = 0;

            String[] strIngredients = {drink.getStrIngredient1(), drink.getStrIngredient2(),drink.getStrIngredient3(),drink.getStrIngredient4(),
                    drink.getStrIngredient5(),drink.getStrIngredient6(),drink.getStrIngredient7(),drink.getStrIngredient8(),drink.getStrIngredient9(),
                    drink.getStrIngredient10(),drink.getStrIngredient11(),drink.getStrIngredient12(),drink.getStrIngredient13(),
                    drink.getStrIngredient14(),drink.getStrIngredient15()};

            for (String string:strIngredients){
                if (string!=null && !string.equals("")){
                    ingredient_counter+=1;

                    for (Ingredient ingredient:theUser.getIngredients()){

                        if (ingredient.getStrIngredient()!=null){
                            if (ingredient.getStrIngredient().toLowerCase().equals(string.toLowerCase())){
                                match_counter+=1;
                            }
                        }

                    }
                }

            }
            drink.setScore(ingredient_counter-match_counter);
        }
    }
}
