package com.luispintodesa.bartender.models.manipulation;

import com.luispintodesa.bartender.models.Drink;

import java.util.ArrayList;

public class DrinkListDivider {

    public static ArrayList<ArrayList<Drink>> divide(ArrayList<Drink> original) {

        ArrayList<Drink> one = new ArrayList<Drink>();
        ArrayList<Drink> two = new ArrayList<Drink>();
        ArrayList<Drink> three = new ArrayList<Drink>();

        ArrayList<ArrayList<Drink>> lists = new ArrayList<ArrayList<Drink>>();

        int counter = 0;
        for (Drink i : original) {
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
