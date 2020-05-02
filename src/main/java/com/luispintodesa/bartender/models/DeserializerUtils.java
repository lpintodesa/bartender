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
import java.util.List;

public class DeserializerUtils {

    //TODO: Explore using ObjectReader.
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // TODO: Unwrap root using UNWRAP_ROOT_VALUE annotation to improve performance, elegance and dispense with superfluous wrapper classes.
    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private static final String URL_FIRST_HALF="https://www.thecocktaildb.com/api/json/v2/";
    
    public static Object getAPIFile (){

        try {
            return new ClassPathResource("API_Key.txt").getFile();
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static String getKey() {

        try (BufferedReader line = new BufferedReader(new FileReader((File)getAPIFile()));){
            return line.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static List<IngredientInList> listAllIngredients() {

        List<IngredientInList> allIngredients = new ArrayList<>();

        try {
            URL ingredientList = new URL(URL_FIRST_HALF+getKey()+"/list.php?i=list");
            IngredientInListWrapper listIngredients= MAPPER.readValue(ingredientList, IngredientInListWrapper.class);
            allIngredients=listIngredients.getDrinks();
        } catch (IOException e){
            allIngredients.clear();
        }
        return allIngredients;
    }

    public static Drink searchDrinkById(Integer id) {

        return searchDrinks(URL_FIRST_HALF+getKey()+"/lookup.php?i="+id).get(0);
    }

    public static List<Drink> searchDrinkByMultipleIngredients(String name) {

        return searchDrinks(URL_FIRST_HALF+getKey()+"/filter.php?i="+ name);
    }

    public static List<Drink> searchDrinkByName(String name) {

        return searchDrinks(URL_FIRST_HALF+getKey()+"/search.php?s="+ name);
    }

    public static List<Drink> searchDrinks(String url) {

        List<Drink> drinks = new ArrayList<>();

        try {
            URL drinkSearchURL = new URL (url);
            DrinkWrapper drinkWrapper = MAPPER.readValue(drinkSearchURL, DrinkWrapper.class);
            drinks = drinkWrapper.getDrinks();

        } catch (IOException e) {
            drinks.clear();
        }
        return drinks;
    }

    public static Ingredient searchIngredientByName(String name) {

        List<Ingredient> ingredients = new ArrayList<>();

        try {
            URL ingredientList = new URL(URL_FIRST_HALF+getKey()+"/search.php?i="+ SpaceToUnderscoreConverter.convert((name)));
            IngredientWrapper ingredientWrapper= MAPPER.readValue(ingredientList, IngredientWrapper.class);
            ingredients=ingredientWrapper.getIngredient();

        } catch (IOException e){
            ingredients.clear();
        }
        return ingredients.get(0);
    }

    private DeserializerUtils() {
    }
}
