package com.luispintodesa.bartender.models.jsontopojos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luispintodesa.bartender.models.DrinkInList;
import com.luispintodesa.bartender.models.wrappers.DrinkInListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MultiIngredientJSONtoPOJOs {

    public static Object convert(String name) {

        try {
            File file = new ClassPathResource("API_Key.txt").getFile();
            BufferedReader line = new BufferedReader(new FileReader(file));
            String key = line .readLine();


            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            URL drinkList = new URL("https://www.thecocktaildb.com/api/json/v2/"+key+"/filter.php?i="+ name);
            DrinkInListWrapper drinkInListWrapper=mapper.readValue(drinkList, DrinkInListWrapper.class);
            ArrayList<DrinkInList> drinks=drinkInListWrapper.getDrinks();
            return drinks;
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
