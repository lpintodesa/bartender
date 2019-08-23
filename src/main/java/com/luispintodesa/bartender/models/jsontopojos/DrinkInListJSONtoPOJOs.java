package com.luispintodesa.bartender.models.jsontopojos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DrinkInListJSONtoPOJOs {

    public static Object convert(String name) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            URL drinkList = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+ name);
            DrinkInListWrapper drinkInListWrapper=mapper.readValue(drinkList, DrinkInListWrapper.class);
            ArrayList<DrinkInList> drinks=drinkInListWrapper.getDrinks();
            return drinks;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
