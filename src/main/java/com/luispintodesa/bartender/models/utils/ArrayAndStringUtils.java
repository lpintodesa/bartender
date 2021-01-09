package com.luispintodesa.bartender.models.utils;

import com.luispintodesa.bartender.models.Drink;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArrayAndStringUtils {

  private ArrayAndStringUtils() {}

  public static Map<Integer, List<Drink>> divideInThree(List<Drink> original) {

    return original.stream().collect(Collectors.groupingBy(drink -> original.indexOf(drink) % 3));
  }

  public static String replaceWhitespaceWithUnderscore(String string) {

    return string.replaceAll("\\s", "_");
  }

  public static String removeBracketsAndWhiteSpace(String string) {
    return string.replaceAll("[\\[\\]]", "").replaceAll("(,\\s)", ",");
  }

  public static String processMultiIngredientSearchInput(String string) {
    return replaceWhitespaceWithUnderscore(removeBracketsAndWhiteSpace(string));
  }
}
