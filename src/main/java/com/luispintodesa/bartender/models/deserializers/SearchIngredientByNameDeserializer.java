package com.luispintodesa.bartender.models.deserializers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.wrappers.IngredientWrapper;
import com.luispintodesa.bartender.models.manipulation.SpaceToUnderscore;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SearchIngredientByNameDeserializer {

    public static Object convert(String name) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            URL ingredientList = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?i="+ SpaceToUnderscore.convert((name)));
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
