package com.luispintodesa.bartender.models.jsontopojos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.DrinkID;
import com.luispintodesa.bartender.models.DrinkInList;
import com.luispintodesa.bartender.models.wrappers.DrinkIDWrapper;
import com.luispintodesa.bartender.models.wrappers.DrinkInListWrapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DrinkIDJSONtoPOJOs {

    public static Object convert(String url) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            URL drinkIDs = new URL (url);
            DrinkIDWrapper drinkIDWrapper = mapper.readValue(drinkIDs, DrinkIDWrapper.class);
            ArrayList<DrinkID> drinks = drinkIDWrapper.getDrinks();

            ArrayList<Integer> ids = new ArrayList<Integer>();

            for (DrinkID drink : drinks) {
                ids.add(drink.getIdDrink());
            }

            return ids;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
