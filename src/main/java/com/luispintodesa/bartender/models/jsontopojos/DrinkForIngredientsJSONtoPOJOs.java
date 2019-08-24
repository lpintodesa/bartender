package com.luispintodesa.bartender.models.jsontopojos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.DrinkForIngredients;
import com.luispintodesa.bartender.models.DrinkInList;
import com.luispintodesa.bartender.models.wrappers.DrinkForIngredientsWrapper;
import com.luispintodesa.bartender.models.wrappers.DrinkInListWrapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DrinkForIngredientsJSONtoPOJOs {

    public static Object convert(Integer id) {

        int intId = (int) id;
        String strId = String.valueOf(intId);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            URL drinkList = new URL("https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + strId);
            DrinkForIngredientsWrapper drinkForIngredientsWrapper = mapper.readValue(drinkList, DrinkForIngredientsWrapper.class);
            ArrayList<DrinkForIngredients> drinks = drinkForIngredientsWrapper.getDrinks();
            DrinkForIngredients drink = drinks.get(0);
            return drink;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
