package com.luispintodesa.bartender.models.deserializers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.wrappers.DrinkWrapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SearchDrinkByIdDeserializer {

    public static Object convert(Integer id) {

        int intId = (int) id;
        String strId = String.valueOf(intId);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            URL drinkList = new URL("https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + strId);
            DrinkWrapper drinkWrapper = mapper.readValue(drinkList, DrinkWrapper.class);
            ArrayList<Drink> drinks = drinkWrapper.getDrinks();
            Drink drink = drinks.get(0);
            return drink;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
