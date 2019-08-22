package com.luispintodesa.bartender.models.jsontopojos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.IngredientWrapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class IngredientJSONtoPOJO {

    public static Object convert(String name) {

        String processedName="";

        for (char c: name.toCharArray()){
            if (Character.isWhitespace(c)){
                processedName+="_";
            } else {
                processedName+=c;
            }
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL ingredientList = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?i="+processedName);
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
