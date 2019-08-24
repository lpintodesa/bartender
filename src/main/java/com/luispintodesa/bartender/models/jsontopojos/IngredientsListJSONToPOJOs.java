package com.luispintodesa.bartender.models.jsontopojos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.wrappers.IngredientListWrapper;
import com.luispintodesa.bartender.models.ListIngredient;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class IngredientsListJSONToPOJOs {

    public static Object convert() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            URL ingredientList = new URL("https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list");
            IngredientListWrapper listIngredients=mapper.readValue(ingredientList, IngredientListWrapper.class);
            ArrayList<ListIngredient> ingredients=listIngredients.getDrinks();
            return ingredients;
        } catch (IOException e){
            e.printStackTrace();
        }
    return "";
    }
}