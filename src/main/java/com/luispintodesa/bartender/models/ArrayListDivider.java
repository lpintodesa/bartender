package com.luispintodesa.bartender.models;

import java.util.ArrayList;

public class ArrayListDivider {

    public static ArrayList<ArrayList<DrinkInList>> divide(ArrayList<DrinkInList> original) {

        ArrayList<DrinkInList> one = new ArrayList<DrinkInList>();
        ArrayList<DrinkInList> two = new ArrayList<DrinkInList>();
        ArrayList<DrinkInList> three = new ArrayList<DrinkInList>();

        ArrayList<ArrayList<DrinkInList>> lists = new ArrayList<ArrayList<DrinkInList>>();

        int counter = 0;
        for (DrinkInList i : original) {
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
