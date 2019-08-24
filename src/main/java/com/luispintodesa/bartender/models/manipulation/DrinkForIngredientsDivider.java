package com.luispintodesa.bartender.models.manipulation;

import com.luispintodesa.bartender.models.DrinkForIngredients;

import java.util.ArrayList;

public class DrinkForIngredientsDivider {

    public static ArrayList<ArrayList<DrinkForIngredients>> divide(ArrayList<DrinkForIngredients> original) {

        ArrayList<DrinkForIngredients> one = new ArrayList<DrinkForIngredients>();
        ArrayList<DrinkForIngredients> two = new ArrayList<DrinkForIngredients>();
        ArrayList<DrinkForIngredients> three = new ArrayList<DrinkForIngredients>();

        ArrayList<ArrayList<DrinkForIngredients>> lists = new ArrayList<ArrayList<DrinkForIngredients>>();

        int counter = 0;
        for (DrinkForIngredients i : original) {
            if (counter < 3) {
                counter += 1;
            } else {
                counter = 1;
            }
            if (counter == 1) {
                one.add(i);
            } else if (counter == 2) {
                two.add(i);
            } else if (counter == 3) {
                three.add(i);
            }
        }

        lists.add(one);
        lists.add(two);
        lists.add(three);

        return lists;
    }
}
