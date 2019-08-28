package com.luispintodesa.bartender.models.deserializers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.*;
import com.luispintodesa.bartender.models.wrappers.DrinkWrapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SearchDrinkByNameDeserializer {

    public static Object convert(String name) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            URL drinkList = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+ name);
            DrinkWrapper drinkWrapper =mapper.readValue(drinkList, DrinkWrapper.class);
            ArrayList<Drink> drinks= drinkWrapper.getDrinks();
            return drinks;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
