package com.luispintodesa.bartender.models.manipulation;

import com.luispintodesa.bartender.models.Ingredient;
import com.luispintodesa.bartender.models.IngredientInList;

import java.util.List;

public class DuplicateChecker {

    public static boolean checkIngredient (String input, List<Ingredient> list) {

        for (Ingredient i : list) {
            if (i.getStrIngredient().equalsIgnoreCase(input)) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkIngredientInList (String input, List<IngredientInList> list) {

        for (IngredientInList i : list) {
            if (i.getStrIngredient1()!=null && i.getStrIngredient1().equalsIgnoreCase(input)) {
                    return true;
            }
        }
        return false;
    }

    private DuplicateChecker() {
    }
}

