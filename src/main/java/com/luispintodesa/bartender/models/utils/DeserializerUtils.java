package com.luispintodesa.bartender.models.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.luispintodesa.bartender.models.Drink;
import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.IngredientInList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.luispintodesa.bartender.models.Constants.EMPTY_STRING;
import static com.luispintodesa.bartender.models.utils.ArrayAndStringUtils.replaceWhitespaceWithUnderscore;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeserializerUtils {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final JsonFactory JSON_FACTORY = new JsonFactory();
  private static final ObjectReader DRINK_READER =
      MAPPER.readerFor(new TypeReference<List<Drink>>() {}).withRootName("drinks");
  private static final ObjectReader INGREDIENT_IN_LIST_READER =
      MAPPER.readerFor(new TypeReference<List<IngredientInList>>() {}).withRootName("drinks");
  private static final String URL_FIRST_HALF = "https://www.thecocktaildb.com/api/json/v2/";
  private static final String URL_SEGMENT_LIST = "/list.php?i=list";
  private static final String URL_SEGMENT_SEARCH_DRINK_BY_NAME = "/search.php?s=";
  private static final String URL_SEGMENT_SEARCH_INGREDIENT_BY_NAME = "/search.php?i=";
  private static final String URL_SEGMENT_SEARCH_DRINK_BY_INGREDIENTS = "/filter.php?i=";
  private static final String URL_SEGMENT_SEARCH_DRINK_BY_ID = "/lookup.php?i=";
  private static final String API_KEY_FILE = "API_Key.txt";

  public static Object getAPIFile() {

    try {
      return new ClassPathResource(API_KEY_FILE).getFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return EMPTY_STRING;
  }

  public static String getKey() {

    try (BufferedReader line = new BufferedReader(new FileReader((File) getAPIFile()))) {
      return line.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return EMPTY_STRING;
  }

  public static List<IngredientInList> listAllIngredients() {

    List<IngredientInList> allIngredients = new ArrayList<>();

    try {
      URL ingredientList = new URI(URL_FIRST_HALF + getKey() + URL_SEGMENT_LIST).toURL();
      allIngredients = INGREDIENT_IN_LIST_READER.readValue(ingredientList);
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
    return allIngredients;
  }

  public static Drink searchDrinkById(Integer id) {

    try {
      URL drinkSearchURL = new URI(URL_FIRST_HALF + getKey() + URL_SEGMENT_SEARCH_DRINK_BY_ID + id).toURL();

      try (JsonParser parser = JSON_FACTORY.createParser(drinkSearchURL)) {
        JsonToken jsonToken;
        parser.nextToken();

        do {
          jsonToken = parser.nextToken();
        } while (!JsonToken.START_OBJECT.equals(jsonToken));

        return MAPPER.readValue(parser, Drink.class);
      }
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
      return Drink.NOT_FOUND;
  }

  public static List<Drink> searchDrinkByMultipleIngredients(String ingredientNames) {

    return searchDrinks(URL_FIRST_HALF + getKey() + URL_SEGMENT_SEARCH_DRINK_BY_INGREDIENTS + ingredientNames);
  }

  public static List<Drink> searchDrinkByName(String name) {

    return searchDrinks(URL_FIRST_HALF + getKey() + URL_SEGMENT_SEARCH_DRINK_BY_NAME + name);
  }

  public static List<Integer> searchDrinkIdsBySingleIngredient(Ingredient ingredient) {

    List<Integer> list = new ArrayList<>();

    try {
      URL drinkSearchURL = new URI(URL_FIRST_HALF + getKey() + URL_SEGMENT_SEARCH_DRINK_BY_INGREDIENTS + replaceWhitespaceWithUnderscore(ingredient.getName())).toURL();

      try (JsonParser parser = JSON_FACTORY.createParser(drinkSearchURL)) {

        while (!parser.isClosed()) {
          JsonToken jsonToken = parser.nextToken();

          if (JsonToken.FIELD_NAME.equals(jsonToken)) {
            String fieldName = parser.currentName();
            parser.nextToken();
            if ("idDrink".equals(fieldName)) {
              list.add(parser.getValueAsInt());
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  public static List<Drink> searchDrinks(String url) {

    List<Drink> drinks = new ArrayList<>();

    try {
      URL drinkSearchURL = new URI(url).toURL();
      drinks = DRINK_READER.readValue(drinkSearchURL);

    } catch (MismatchedInputException e) {
      return drinks;
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
      return drinks;
  }

  public static Ingredient searchIngredientByName(String name) {

    try {
      URL ingredientList = new URI(URL_FIRST_HALF + getKey() + URL_SEGMENT_SEARCH_INGREDIENT_BY_NAME + replaceWhitespaceWithUnderscore((name))).toURL();

      try (JsonParser parser = JSON_FACTORY.createParser(ingredientList)) {
        JsonToken jsonToken;
        parser.nextToken();

        do {
          jsonToken = parser.nextToken();
        } while (!JsonToken.START_OBJECT.equals(jsonToken));

        return MAPPER.readValue(parser, Ingredient.class);
      }
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }
      return new Ingredient();
  }
}
