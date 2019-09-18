package com.luispintodesa.bartender.models.manipulation;

import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.IngredientInList;

import java.util.ArrayList;
import java.util.List;

public class DuplicateChecker {

    public static boolean checkIngredient (String input, List<Ingredient> list) {

        for (Ingredient i : list) {
            if (i.getStrIngredient().toLowerCase().equals(input.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkIngredientInList (String input, ArrayList<IngredientInList> list) {

        for (IngredientInList i : list) {
            if (i.getStrIngredient1()!=null){
                if (i.getStrIngredient1().toLowerCase().equals(input.toLowerCase())) {
                    return true;
                }
            }
        }

        return false;
    }

}

