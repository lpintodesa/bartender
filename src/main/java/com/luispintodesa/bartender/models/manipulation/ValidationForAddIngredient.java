package com.luispintodesa.bartender.models.manipulation;

import com.luispintodesa.bartender.models.IngredientInList;

import java.util.ArrayList;

public class ValidationForAddIngredient {

    public static boolean check (String input, ArrayList<IngredientInList> list) {

        for (IngredientInList i : list) {
            if (i.getStrIngredient1().toLowerCase().equals(input.toLowerCase())) {
                return true;
            }
        }

        return false;
        }

    }