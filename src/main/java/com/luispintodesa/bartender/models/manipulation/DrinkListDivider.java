package com.luispintodesa.bartender.models.manipulation;

import com.luispintodesa.bartender.models.DrinkDetails;

import java.util.ArrayList;

public class DrinkListDivider {

    public static ArrayList<ArrayList<DrinkDetails>> divide(ArrayList<DrinkDetails> original) {

        ArrayList<DrinkDetails> one = new ArrayList<DrinkDetails>();
        ArrayList<DrinkDetails> two = new ArrayList<DrinkDetails>();
        ArrayList<DrinkDetails> three = new ArrayList<DrinkDetails>();

        ArrayList<ArrayList<DrinkDetails>> lists = new ArrayList<ArrayList<DrinkDetails>>();

        int counter = 0;
        for (DrinkDetails i : original) {
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
