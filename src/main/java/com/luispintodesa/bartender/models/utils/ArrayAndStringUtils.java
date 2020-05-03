package com.luispintodesa.bartender.models.utils;

import com.luispintodesa.bartender.models.Drink;

import java.util.Arrays;
import java.util.List;

public class ArrayAndStringUtils {

    public static List<List<Drink>> divideInThree(List<Drink> original) {

      List<Drink> one;
      List<Drink> two;
      List<Drink> three;

      double sizeDividedByThree = original.size() / 3.0;

      int sizeDividedByThreeRounded = (int) Math.round(sizeDividedByThree);

      if (sizeDividedByThreeRounded < sizeDividedByThree) {
        one = original.subList(0, sizeDividedByThreeRounded + 1);
        two = original.subList(sizeDividedByThreeRounded + 1, 2 * sizeDividedByThreeRounded + 1);
        three = original.subList(2 * sizeDividedByThreeRounded + 1, original.size());
      } else {
        one = original.subList(0, sizeDividedByThreeRounded);
        two = original.subList(sizeDividedByThreeRounded, 2 * sizeDividedByThreeRounded);
        three = original.subList(2 * sizeDividedByThreeRounded, original.size());
      }

      return Arrays.asList(one, two, three);
    }

    public static String convert(String string) {

      return string.replaceAll("\\s", "_");
    }
}
