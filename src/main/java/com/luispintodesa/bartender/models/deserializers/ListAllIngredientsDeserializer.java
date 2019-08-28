package com.luispintodesa.bartender.models.deserializers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.wrappers.IngredientInListWrapper;
import com.luispintodesa.bartender.models.IngredientInList;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ListAllIngredientsDeserializer {

    public static Object convert() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            URL ingredientList = new URL("https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list");
            IngredientInListWrapper listIngredients=mapper.readValue(ingredientList, IngredientInListWrapper.class);
            ArrayList<IngredientInList> ingredients=listIngredients.getDrinks();
            return ingredients;
        } catch (IOException e){
            e.printStackTrace();
        }
    return "";
    }
}