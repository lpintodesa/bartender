package com.luispintodesa.bartender.models.deserializers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.Drink;
import org.springframework.core.io.ClassPathResource;
import com.luispintodesa.bartender.models.wrappers.DrinkWrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SearchDrinkByMultipleIngredientsDeserializer {

    public static Object convert(String name) {

        try {
            File file = new ClassPathResource("API_Key.txt").getFile();
            BufferedReader line = new BufferedReader(new FileReader(file));
            String key = line .readLine();


            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            URL drinkList = new URL("https://www.thecocktaildb.com/api/json/v2/"+key+"/filter.php?i="+ name);
            DrinkWrapper drinkWrapper =mapper.readValue(drinkList, DrinkWrapper.class);
            ArrayList<Drink> drinks= drinkWrapper.getDrinks();

            return drinks;

        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
