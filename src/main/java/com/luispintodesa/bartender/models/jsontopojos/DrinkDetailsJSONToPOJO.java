package com.luispintodesa.bartender.models.jsontopojos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.DrinkDetails;
import com.luispintodesa.bartender.models.DrinkForIngredients;
import com.luispintodesa.bartender.models.wrappers.DrinkDetailsWrapper;
import com.luispintodesa.bartender.models.wrappers.DrinkForIngredientsWrapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DrinkDetailsJSONToPOJO {

    public static Object convert(Integer id) {

        int intId = (int) id;
        String strId = String.valueOf(intId);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            URL drinkList = new URL("https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + strId);
            DrinkDetailsWrapper drinkDetailsWrapper = mapper.readValue(drinkList, DrinkDetailsWrapper.class);
            ArrayList<DrinkDetails> drinks = drinkDetailsWrapper.getDrinks();
            DrinkDetails drink = drinks.get(0);
            return drink;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
