package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.manipulation.SpaceToUnderscoreConverter;
import com.luispintodesa.bartender.models.wrappers.DrinkWrapper;
import com.luispintodesa.bartender.models.wrappers.IngredientInListWrapper;
import com.luispintodesa.bartender.models.wrappers.IngredientWrapper;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Deserializer {
    
    private static String getKey(){
        try {
            File file = new ClassPathResource("API_Key.txt").getFile();
            BufferedReader line = new BufferedReader(new FileReader(file));
            String key = line .readLine();
            return key;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static Object listAllIngredients() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            URL ingredientList = new URL("https://www.thecocktaildb.com/api/json/v2/"+getKey()+"/list.php?i=list");
            IngredientInListWrapper listIngredients=mapper.readValue(ingredientList, IngredientInListWrapper.class);
            ArrayList<IngredientInList> ingredients=listIngredients.getDrinks();
            return ingredients;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static Object searchDrinkById(Integer id) {

        int intId = (int) id;
        String strId = String.valueOf(intId);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            URL drinkList = new URL("https://www.thecocktaildb.com/api/json/v2/"+getKey()+"/lookup.php?i=" + strId);
            DrinkWrapper drinkWrapper = mapper.readValue(drinkList, DrinkWrapper.class);
            ArrayList<Drink> drinks = drinkWrapper.getDrinks();
            Drink drink = drinks.get(0);
            return drink;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Object searchDrinkByMultipleIngredients(String name) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            URL drinkList = new URL("https://www.thecocktaildb.com/api/json/v2/"+getKey()+"/filter.php?i="+ name);
            DrinkWrapper drinkWrapper =mapper.readValue(drinkList, DrinkWrapper.class);
            ArrayList<Drink> drinks= drinkWrapper.getDrinks();

            return drinks;

        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static Object searchDrinkByName(String name) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            URL drinkList = new URL("https://www.thecocktaildb.com/api/json/v2/"+getKey()+"/search.php?s="+ name);
            DrinkWrapper drinkWrapper =mapper.readValue(drinkList, DrinkWrapper.class);
            ArrayList<Drink> drinks= drinkWrapper.getDrinks();
            return drinks;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static Object searchDrinkBySingleIngredient(String url) {

        ArrayList<Integer> ids = new ArrayList<Integer>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            URL drinkIDs = new URL (url);
            DrinkWrapper drinkWrapper = mapper.readValue(drinkIDs, DrinkWrapper.class);
            ArrayList<Drink> drinks = drinkWrapper.getDrinks();

            for (Drink drink : drinks) {
                ids.add(drink.getIdDrink());
            }

        } catch (IOException e) {
            ids.clear();
        }
        return ids;
    }

    public static Object searchIngredientByName(String name) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            URL ingredientList = new URL("https://www.thecocktaildb.com/api/json/v2/"+getKey()+"/search.php?i="+ SpaceToUnderscoreConverter.convert((name)));
            IngredientWrapper ingredientWrapper=mapper.readValue(ingredientList, IngredientWrapper.class);
            ArrayList<Ingredient> ingredients=ingredientWrapper.getIngredient();
            Ingredient ingredient = ingredients.get(0);
            return ingredient;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
