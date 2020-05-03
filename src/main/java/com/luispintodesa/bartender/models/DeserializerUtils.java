package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.luispintodesa.bartender.models.manipulation.SpaceToUnderscoreConverter;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DeserializerUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    private static final ObjectReader DRINK_READER = MAPPER.readerFor(new TypeReference<List<Drink>>() {}).withRootName("drinks");
    private static final ObjectReader INGREDIENT_READER = MAPPER.readerFor(new TypeReference<List<Ingredient>>() {}).withRootName("ingredients");
    private static final ObjectReader INGREDIENT_IN_LIST_READER = MAPPER.readerFor(new TypeReference<List<IngredientInList>>() {}).withRootName("drinks");

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
            allIngredients=INGREDIENT_IN_LIST_READER.readValue(ingredientList);
        } catch (IOException e){
            e.printStackTrace();
        }
        return allIngredients;
    }

    public static Drink searchDrinkById(Integer id) {

        return searchDrinks(URL_FIRST_HALF+getKey()+"/lookup.php?i="+id).get(0);
    }

    public static List<Drink> searchDrinkByMultipleIngredients(String ingredientNames) {

        return searchDrinks(URL_FIRST_HALF+getKey()+"/filter.php?i="+ ingredientNames);
    }

    public static List<Drink> searchDrinkByName(String name) {

        return searchDrinks(URL_FIRST_HALF+getKey()+"/search.php?s="+ name);
    }

    public static List<Drink> searchDrinkBySingleIngredient(Ingredient ingredient){
        return searchDrinks(URL_FIRST_HALF+getKey()+"/filter.php?i="+ SpaceToUnderscoreConverter.convert(ingredient.getStrIngredient()));
    }

    public static List<Drink> searchDrinks(String url) {

        List<Drink> drinks = new ArrayList<>();

        try {
            URL drinkSearchURL = new URL (url);
            drinks = DRINK_READER.readValue(drinkSearchURL);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return drinks;
    }

    public static Ingredient searchIngredientByName(String name) {

        List<Ingredient> ingredients = new ArrayList<>();

        try {
            URL ingredientList = new URL(URL_FIRST_HALF+getKey()+"/search.php?i="+ SpaceToUnderscoreConverter.convert((name)));
            ingredients= INGREDIENT_READER.readValue(ingredientList);

        } catch (IOException e){
            e.printStackTrace();
        }
        return ingredients.get(0);
    }

    private DeserializerUtils() {
    }
}
