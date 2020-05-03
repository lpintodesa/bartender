package com.luispintodesa.bartender.models.manipulation;

import com.luispintodesa.bartender.models.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkListDivider {

    public static List<List<Drink>> divideInThree(List<Drink> original) {

        List<Drink> one = new ArrayList<>();
        List<Drink> two = new ArrayList<>();
        List<Drink> three = new ArrayList<>();

        List<List<Drink>> lists = new ArrayList<>();

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
            } else {
                three.add(i);
            }
        }

        lists.add(one);
        lists.add(two);
        lists.add(three);

        return lists;
    }

    private DrinkListDivider() {
    }
}
