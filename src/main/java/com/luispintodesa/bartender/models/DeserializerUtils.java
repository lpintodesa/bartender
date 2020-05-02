package com.luispintodesa.bartender.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.manipulation.SpaceToUnderscoreConverter;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Deserializer {

    //TODO: Explore using ObjectReader.
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // TODO: Unwrap root using UNWRAP_ROOT_VALUE annotation to improve performance, elegance and dispense with superfluous wrapper classes.
    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);}

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

    public static Object listAllIngredients() {
        try {
            URL ingredientList = new URL(URL_FIRST_HALF+getKey()+"/list.php?i=list");
            List<IngredientInList> listIngredients= MAPPER.readValue(ingredientList, new TypeReference<List<IngredientInList>>(){});
            return listIngredients;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static Object searchDrinkById(Integer id) {

        String strId = String.valueOf(id);

        try {
            URL drinkList = new URL(URL_FIRST_HALF+getKey()+"/lookup.php?i=" + strId);
            List<Drink> drinks = MAPPER.readValue(drinkList, new TypeReference<List<Drink>>(){});
            return drinks.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Object searchDrinkByMultipleIngredients(String name) {

        try {
            URL drinkList = new URL(URL_FIRST_HALF+getKey()+"/filter.php?i="+ name);
            List<Drink> drinks = MAPPER.readValue(drinkList, new TypeReference<List<Drink>>(){});
            return drinks;

        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static Object searchDrinkByName(String name) {

        try {
            URL drinkList = new URL(URL_FIRST_HALF+getKey()+"/search.php?s="+ name);
            List<Drink> drinks = MAPPER.readValue(drinkList, new TypeReference<List<Drink>>(){});
            return drinks;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static Object searchDrinkBySingleIngredient(String url) {

        List<Drink> drinks = new ArrayList<>();

        try {
            URL drinkIDs = new URL (url);
            drinks = MAPPER.readValue(drinkIDs, new TypeReference<List<Drink>>(){});

        } catch (IOException e) {
            drinks.clear();
        }
        return drinks;
    }

    public static Object searchIngredientByName(String name) {

        try {
            URL ingredientList = new URL(URL_FIRST_HALF+getKey()+"/search.php?i="+ SpaceToUnderscoreConverter.convert((name)));
            List<Ingredient> ingredients=MAPPER.readValue(ingredientList, new TypeReference<List<Ingredient>>(){});
            return ingredients.get(0);
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
