package com.luispintodesa.bartender.models;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.luispintodesa.bartender.models.Ingredient;

import javax.persistence.criteria.CriteriaBuilder;

public class JSONtoPOJOs {

    public static Object convert() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL ingredientList = new URL("https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list");
            IngredientWrapper listIngredients=mapper.readValue(ingredientList, IngredientWrapper.class);
            List<Ingredient> ingredients=listIngredients.getDrinks();
            return ingredients;
        } catch (IOException e){
            e.printStackTrace();
        }
    return "";
    }
}