package com.luispintodesa.bartender.models.deserializers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.wrappers.DrinkWrapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SearchDrinkBySingleIngredientDeserializer {

    public static Object convert(String url) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            URL drinkIDs = new URL (url);
            DrinkWrapper drinkWrapper = mapper.readValue(drinkIDs, DrinkWrapper.class);
            ArrayList<Drink> drinks = drinkWrapper.getDrinks();

            ArrayList<Integer> ids = new ArrayList<Integer>();

            for (Drink drink : drinks) {
                ids.add(drink.getIdDrink());
            }

            return ids;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
